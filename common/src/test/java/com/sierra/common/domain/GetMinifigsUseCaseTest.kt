package com.sierra.common.domain

import com.sierra.common.domain.model.SOME_CATEGORY
import com.sierra.common.domain.model.SOME_MINIFIG
import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

internal class GetMinifigsUseCaseTest {

    private val minifig1 = SOME_MINIFIG.copy(id = "id1", categoryId = "cat1")
    private val minifig2 = SOME_MINIFIG.copy(id = "id2", categoryId = "cat2")
    private val minifigs = listOf(minifig1, minifig2)
    private val categories = listOf(
        SOME_CATEGORY.copy(name = "category1", id = "cat1"),
        SOME_CATEGORY.copy(name = "category2", id = "cat2"),
    )

    private val minifigRepository: MinifigRepository = mock {
        onBlocking { getMinifigList() } doReturn flowOf(minifigs)
    }
    private val categoryRepository: CategoryRepository = mock {
        onBlocking { getCategoryList() } doReturn flowOf(categories)
    }

    private val underTest = GetMinifigsUseCase(
        minifigRepository = minifigRepository,
        categoryRepository = categoryRepository,
    )

    @Test
    fun `should return correct minifigs with correct categories associated`() =
        runTest {
            val list = underTest.invoke().first()

            list.size shouldBeEqualTo 2
            list.first() shouldBeEqualTo minifig1.copy(categoryName = "category1")
            list[1] shouldBeEqualTo minifig2.copy(categoryName = "category2")
        }

    @Test
    fun `should return correct minifigs with no category if not found`() =
        runTest {
            val minifigs = listOf(minifig1, minifig2.copy(categoryId = "unknown_cat"))
            minifigRepository.stub {
                onBlocking { getMinifigList() } doReturn flowOf(minifigs)
            }
            val list = underTest.invoke().first()

            list.size shouldBeEqualTo 2
            list[1] shouldBeEqualTo minifig2.copy(categoryId = "unknown_cat", categoryName = "")
        }

    @Test
    fun `should return new result if minifig list is updated`() =
        runTest {
            minifigRepository.stub {
                onBlocking { getMinifigList() } doReturn flowOf(minifigs, listOf(minifig1))
            }

            underTest.invoke().count() shouldBeEqualTo 2
        }

    @Test
    fun `should return new result if category list is updated`() =
        runTest {
            categoryRepository.stub {
                onBlocking { getCategoryList() } doReturn flowOf(categories, listOf(SOME_CATEGORY))
            }

            underTest.invoke().count() shouldBeEqualTo 2
        }
}

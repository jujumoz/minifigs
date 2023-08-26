package com.sierra.common.domain

import com.sierra.common.domain.model.SOME_CATEGORY
import com.sierra.common.domain.model.SOME_MINIFIG
import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

internal class GetMinifigUseCaseTest {

    private val minifig = SOME_MINIFIG
    private val category = SOME_CATEGORY

    private val minifigRepository: MinifigRepository = mock {
        onBlocking { getMinifigById(any()) } doReturn Result.success(minifig)
    }
    private val categoryRepository: CategoryRepository = mock {
        onBlocking { getCategoryById(minifig.categoryId) } doReturn Result.success(category)
    }

    private val underTest = GetMinifigUseCase(
        minifigRepository = minifigRepository,
        categoryRepository = categoryRepository,
    )

    @Test
    fun `should return success if get minifig and get category are successful`() =
        runTest {
            val expected = minifig.copy(categoryName = category.name)

            underTest.invoke("id") shouldBeEqualTo Result.success(expected)
        }

    @Test
    fun `should return success if category is not found but with no category name`() =
        runTest {
            whenGetCategoryByIdFails()
            val expected = minifig

            underTest.invoke("id") shouldBeEqualTo Result.success(expected)
        }

    @Test
    fun `should return failure if minifig is not found`() =
        runTest {
            val exception = Exception("error")
            whenGetMinifigById(exception)

            underTest.invoke("id") shouldBeEqualTo Result.failure(exception)
        }

    private fun whenGetCategoryByIdFails() {
        categoryRepository.stub {
            onBlocking { getCategoryById(any()) } doReturn Result.failure(Exception())
        }
    }

    private fun whenGetMinifigById(exception: Exception) {
        minifigRepository.stub {
            onBlocking { getMinifigById(any()) } doReturn Result.failure(exception)
        }
    }
}

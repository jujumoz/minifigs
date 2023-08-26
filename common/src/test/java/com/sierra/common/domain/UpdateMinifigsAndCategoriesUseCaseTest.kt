package com.sierra.common.domain

import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub

internal class UpdateMinifigsAndCategoriesUseCaseTest {

    private val minifigRepository: MinifigRepository = mock {
        onBlocking { updateMinifigList() } doReturn Result.success(Unit)
    }
    private val categoryRepository: CategoryRepository = mock {
        onBlocking { updateCategoryList() } doReturn Result.success(Unit)
    }

    private val underTest = UpdateMinifigsAndCategoriesUseCase(
        minifigRepository = minifigRepository,
        categoryRepository = categoryRepository,
    )

    @Test
    fun `should return success if update minifig list and category list are successful`() =
        runTest {
            underTest.invoke() shouldBeEqualTo Result.success(Unit)
        }

    @Test
    fun `should return failure if update minifig list has failed`() = runTest {
        val exception = IllegalArgumentException()
        whenUpdateMinifigListFails(exception)

        underTest.invoke() shouldBeEqualTo Result.failure(exception)
    }

    @Test
    fun `should return failure if update category list has failed`() = runTest {
        val exception = IllegalArgumentException()
        whenUpdateCategoryListFails(exception)

        underTest.invoke() shouldBeEqualTo Result.failure(exception)
    }

    private fun whenUpdateMinifigListFails(exception: Exception) {
        minifigRepository.stub {
            onBlocking { updateMinifigList() } doReturn Result.failure(exception)
        }
    }

    private fun whenUpdateCategoryListFails(exception: Exception) {
        categoryRepository.stub {
            onBlocking { updateCategoryList() } doReturn Result.failure(exception)
        }
    }
}

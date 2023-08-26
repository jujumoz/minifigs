package com.sierra.common.domain

import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import javax.inject.Inject

class UpdateMinifigsAndCategoriesUseCase @Inject constructor(
    private val minifigRepository: MinifigRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(): Result<Unit> {
        val minifigFlow = minifigRepository.updateMinifigList().exceptionOrNull()
        val categoryFlow = categoryRepository.updateCategoryList().exceptionOrNull()

        return when {
            minifigFlow != null -> Result.failure(minifigFlow)
            categoryFlow != null -> Result.failure(categoryFlow)
            else -> Result.success(Unit)
        }
    }
}

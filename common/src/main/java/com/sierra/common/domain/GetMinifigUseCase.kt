package com.sierra.common.domain

import com.sierra.common.domain.model.Minifig
import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import javax.inject.Inject

class GetMinifigUseCase @Inject constructor(
    private val minifigRepository: MinifigRepository,
    private val categoryRepository: CategoryRepository,
) {

    suspend operator fun invoke(id: String): Result<Minifig> {
        val result = minifigRepository.getMinifigById(id)
        return result.fold(
            onSuccess = { minifig -> getCategory(minifig) },
            onFailure = { result }
        )
    }

    private suspend fun getCategory(minifig: Minifig): Result<Minifig> {
        val category = categoryRepository.getCategoryById(minifig.categoryId).getOrNull()
        return Result.success(
            minifig.copy(categoryName = category?.name.orEmpty())
        )
    }
}

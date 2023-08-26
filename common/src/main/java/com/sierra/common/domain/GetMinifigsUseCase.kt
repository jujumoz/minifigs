package com.sierra.common.domain

import com.sierra.common.domain.model.Category
import com.sierra.common.domain.model.Minifig
import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetMinifigsUseCase @Inject constructor(
    private val minifigRepository: MinifigRepository,
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(): Flow<List<Minifig>> {
        val minifigFlow = minifigRepository.getMinifigList()
        val categoryFlow = categoryRepository.getCategoryList()
        return combine(minifigFlow, categoryFlow) { minifigs, categories ->
            getMinifigsWithCategories(minifigs, categories)
        }
    }

    private fun getMinifigsWithCategories(
        minifigs: List<Minifig>,
        categories: List<Category>,
    ): List<Minifig> {
        val minifigsWithCategories = mutableListOf<Minifig>()
        minifigs.forEach { minifig ->
            val category = categories.firstOrNull { it.id == minifig.categoryId }
            minifigsWithCategories.add(
                minifig.copy(categoryName = category?.name.orEmpty())
            )
        }
        return minifigsWithCategories
    }
}

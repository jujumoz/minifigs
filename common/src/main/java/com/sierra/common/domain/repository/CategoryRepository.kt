package com.sierra.common.domain.repository

import com.sierra.common.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategoryList(): Flow<List<Category>>
    suspend fun getCategoryById(id: String): Result<Category>
    suspend fun updateCategoryList(): Result<Unit>
}

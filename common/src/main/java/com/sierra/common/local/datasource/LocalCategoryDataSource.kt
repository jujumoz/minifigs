package com.sierra.common.local.datasource

import androidx.room.withTransaction
import com.sierra.common.domain.model.Category
import com.sierra.common.local.dao.CategoryDao
import com.sierra.common.local.db.AppDatabase
import com.sierra.common.local.mapper.toDomain
import com.sierra.common.local.mapper.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalCategoryDataSource @Inject constructor(
    private val appDatabase: AppDatabase,
    private val categoryDao: CategoryDao,
) {
    suspend fun getCategoryById(id: String): Result<Category> =
        runCatching { categoryDao.getById(id).toDomain() }

    fun getCategoryList(): Flow<List<Category>> =
        categoryDao.getAll().map { it.toDomain() }

    suspend fun saveCategoryList(categories: List<Category>): Result<Unit> =
        appDatabase.withTransaction {
            runCatching { categoryDao.insertAll(*categories.toLocal().toTypedArray()) }
        }
}

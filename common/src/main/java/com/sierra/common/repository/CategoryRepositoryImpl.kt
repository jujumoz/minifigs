package com.sierra.common.repository

import com.sierra.common.domain.model.Category
import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.local.datasource.LocalCategoryDataSource
import com.sierra.common.network.datasource.RemoteCategoryDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
    private val remoteCategoryDataSource: RemoteCategoryDataSource,
    private val localCategoryDataSource: LocalCategoryDataSource,
) : CategoryRepository {

    override suspend fun updateCategoryList(): Result<Unit> =
        remoteCategoryDataSource.getCategoryList()
            .map { result -> localCategoryDataSource.saveCategoryList(result) }

    override fun getCategoryList(): Flow<List<Category>> =
        localCategoryDataSource.getCategoryList()

    override suspend fun getCategoryById(id: String): Result<Category> =
        localCategoryDataSource.getCategoryById(id)
}

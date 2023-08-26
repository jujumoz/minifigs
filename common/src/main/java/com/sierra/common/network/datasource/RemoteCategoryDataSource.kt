package com.sierra.common.network.datasource

import com.sierra.common.domain.model.Category
import com.sierra.common.network.mapper.toDomain
import com.sierra.common.network.service.ApiService
import javax.inject.Inject

internal class RemoteCategoryDataSource @Inject constructor(
    private val service: ApiService,
) {

    suspend fun getCategoryList(): Result<List<Category>> =
        runCatching { service.getCategories().toDomain() }
}

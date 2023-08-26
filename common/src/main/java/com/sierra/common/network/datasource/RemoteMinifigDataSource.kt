package com.sierra.common.network.datasource

import com.sierra.common.domain.model.Minifig
import com.sierra.common.network.mapper.toDomain
import com.sierra.common.network.service.ApiService
import javax.inject.Inject

internal class RemoteMinifigDataSource @Inject constructor(
    private val service: ApiService,
) {

    suspend fun getMinifigList(): Result<List<Minifig>> =
        runCatching { service.getMinifigs().toDomain() }
}

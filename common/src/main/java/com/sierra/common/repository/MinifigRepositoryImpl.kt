package com.sierra.common.repository

import com.sierra.common.domain.model.Minifig
import com.sierra.common.domain.repository.MinifigRepository
import com.sierra.common.local.datasource.LocalMinifigDataSource
import com.sierra.common.network.datasource.RemoteMinifigDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class MinifigRepositoryImpl @Inject constructor(
    private val remoteMinifigDataSource: RemoteMinifigDataSource,
    private val localMinifigDataSource: LocalMinifigDataSource,
) : MinifigRepository {

    override suspend fun updateMinifigList(): Result<Unit> =
        remoteMinifigDataSource.getMinifigList()
            .map { result -> localMinifigDataSource.saveMinifigList(result) }

    override fun getMinifigList(): Flow<List<Minifig>> =
        localMinifigDataSource.getMinifigList()

    override suspend fun getMinifigById(id: String): Result<Minifig> =
        localMinifigDataSource.getMinifigById(id)
}

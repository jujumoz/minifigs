package com.sierra.common.local.datasource

import androidx.room.withTransaction
import com.sierra.common.domain.model.Minifig
import com.sierra.common.local.dao.MinifigDao
import com.sierra.common.local.db.AppDatabase
import com.sierra.common.local.mapper.toDomain
import com.sierra.common.local.mapper.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalMinifigDataSource @Inject constructor(
    private val appDatabase: AppDatabase,
    private val minifigDao: MinifigDao,
) {
    suspend fun getMinifigById(id: String): Result<Minifig> =
        runCatching { minifigDao.getById(id).toDomain() }

    fun getMinifigList(): Flow<List<Minifig>> =
        minifigDao.getAll().map { it.toDomain() }

    suspend fun saveMinifigList(minifigs: List<Minifig>): Result<Unit> =
        appDatabase.withTransaction {
            runCatching { minifigDao.insertAll(*minifigs.toLocal().toTypedArray()) }
        }
}

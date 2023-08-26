package com.sierra.common.domain.repository

import com.sierra.common.domain.model.Minifig
import kotlinx.coroutines.flow.Flow

interface MinifigRepository {
    fun getMinifigList(): Flow<List<Minifig>>
    suspend fun getMinifigById(id: String): Result<Minifig>
    suspend fun updateMinifigList(): Result<Unit>
}

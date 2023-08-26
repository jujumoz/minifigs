package com.sierra.common.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sierra.common.local.model.LocalMinifig
import kotlinx.coroutines.flow.Flow

@Dao
interface MinifigDao {
    @Query("SELECT * FROM minifig")
    fun getAll(): Flow<List<LocalMinifig>>

    @Query("SELECT * FROM minifig WHERE id = :id")
    suspend fun getById(id: String): LocalMinifig

    @Upsert
    suspend fun insertAll(vararg minifigs: LocalMinifig)
}

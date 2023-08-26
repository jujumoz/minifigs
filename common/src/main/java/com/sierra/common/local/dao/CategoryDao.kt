package com.sierra.common.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.sierra.common.local.model.LocalCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun getAll(): Flow<List<LocalCategory>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getById(id: String): LocalCategory

    @Upsert
    suspend fun insertAll(vararg categories: LocalCategory)
}

package com.sierra.common.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sierra.common.local.dao.CategoryDao
import com.sierra.common.local.dao.MinifigDao
import com.sierra.common.local.model.LocalCategory
import com.sierra.common.local.model.LocalMinifig

@Database(
    entities = [LocalMinifig::class, LocalCategory::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun minifigDao(): MinifigDao
    abstract fun categoryDao(): CategoryDao
}

package com.sierra.common.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class LocalCategory(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
)

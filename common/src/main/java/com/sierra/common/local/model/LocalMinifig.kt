package com.sierra.common.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minifig")
data class LocalMinifig(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "categoryId") val categoryId: String,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "weight") val weight: String,
)

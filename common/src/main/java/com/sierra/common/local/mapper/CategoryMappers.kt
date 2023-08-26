package com.sierra.common.local.mapper

import com.sierra.common.domain.model.Category
import com.sierra.common.local.model.LocalCategory

fun List<LocalCategory>.toDomain(): List<Category> = map { it.toDomain() }

fun LocalCategory.toDomain() =
    Category(
        id = id,
        name = name,
    )

fun List<Category>.toLocal(): List<LocalCategory> = map { it.toLocal() }

fun Category.toLocal() =
    LocalCategory(
        id = id,
        name = name,
    )
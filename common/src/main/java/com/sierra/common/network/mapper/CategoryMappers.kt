package com.sierra.common.network.mapper

import com.sierra.common.domain.model.Category
import com.sierra.common.network.model.RemoteCategory

fun List<RemoteCategory>.toDomain(): List<Category> = map { it.toDomain() }

fun RemoteCategory.toDomain() =
    Category(
        id = id,
        name = name,
    )

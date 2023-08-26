package com.sierra.common.network.mapper

import com.sierra.common.domain.model.Minifig
import com.sierra.common.network.model.RemoteMinifig

fun List<RemoteMinifig>.toDomain(): List<Minifig> = map { it.toDomain() }

fun RemoteMinifig.toDomain() =
    Minifig(
        type = type,
        id = id,
        name = name,
        categoryId = categoryId,
        year = year,
        weight = weight,
    )

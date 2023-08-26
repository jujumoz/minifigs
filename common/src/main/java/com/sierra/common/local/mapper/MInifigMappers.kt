package com.sierra.common.local.mapper

import com.sierra.common.domain.model.Minifig
import com.sierra.common.local.model.LocalMinifig

fun List<LocalMinifig>.toDomain(): List<Minifig> = map { it.toDomain() }

fun LocalMinifig.toDomain() =
    Minifig(
        type = type,
        id = id,
        name = name,
        categoryId = categoryId,
        year = year,
        weight = weight,
    )

fun List<Minifig>.toLocal(): List<LocalMinifig> = map { it.toLocal() }

fun Minifig.toLocal() =
    LocalMinifig(
        type = type,
        id = id,
        name = name,
        categoryId = categoryId,
        year = year,
        weight = weight,
    )
package com.sierra.common.domain.model

import com.sierra.common.local.model.LocalMinifig
import com.sierra.common.network.model.RemoteMinifig

val SOME_MINIFIG = Minifig(
    type = "voluptatum",
    id = "id",
    name = "Dora Guthrie",
    categoryId = "cat1",
    categoryName = "",
    year = "2003",
    weight = "8",
)

val SOME_REMOTE_MINIFIG = RemoteMinifig(
    type = "voluptatum",
    id = "id",
    name = "Dora Guthrie",
    categoryId = "cat1",
    year = "2003",
    weight = "8",
)

val SOME_LOCAL_MINIFIG = LocalMinifig(
    type = "voluptatum",
    id = "id",
    name = "Dora Guthrie",
    categoryId = "cat1",
    year = "2003",
    weight = "8",
)

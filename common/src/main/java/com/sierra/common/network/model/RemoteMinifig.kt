package com.sierra.common.network.model

import com.google.gson.annotations.SerializedName

data class RemoteMinifig(
    @SerializedName("ITEMTYPE") val type: String,
    @SerializedName("ITEMID") val id: String,
    @SerializedName("ITEMNAME") val name: String,
    @SerializedName("CATEGORY") val categoryId: String,
    @SerializedName("ITEMYEAR") val year: String,
    @SerializedName("ITEMWEIGHT") val weight: String,
)

package com.sierra.common.network.model

import com.google.gson.annotations.SerializedName

data class RemoteCategory(
    @SerializedName("CATEGORY") val id: String,
    @SerializedName("CATEGORYNAME") val name: String,
)

package com.sierra.common.network.service

import com.sierra.common.network.model.RemoteCategory
import com.sierra.common.network.model.RemoteMinifig
import retrofit2.http.GET

internal interface ApiService {

    @GET("/uc?id=1jdj4Kc46vhvh44ycVntgGO_IZNxxO6PJ&export=download")
    suspend fun getMinifigs(): List<RemoteMinifig>

    @GET("/uc?id=18Z-jM40AW5quEjO__YQAwPzcdVkND2Vw&export=download")
    suspend fun getCategories(): List<RemoteCategory>
}

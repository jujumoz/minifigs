package com.sierra.common.di

import com.sierra.common.network.service.ApiService
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    internal fun provideConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    internal fun provideRetrofit(factory: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(factory)
            .build()

    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}

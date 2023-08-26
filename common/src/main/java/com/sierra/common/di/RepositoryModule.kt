package com.sierra.common.di

import com.sierra.common.domain.repository.CategoryRepository
import com.sierra.common.domain.repository.MinifigRepository
import com.sierra.common.repository.CategoryRepositoryImpl
import com.sierra.common.repository.MinifigRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    internal abstract fun provideMinifigRepository(impl: MinifigRepositoryImpl): MinifigRepository

    @Binds
    internal abstract fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository
}

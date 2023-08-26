package com.sierra.common.di

import android.app.Application
import androidx.room.Room
import com.sierra.common.local.dao.CategoryDao
import com.sierra.common.local.dao.MinifigDao
import com.sierra.common.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class DatabaseModule {

    @Singleton
    @Provides
    internal fun provideDatabase(context: Application): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "database-minifig").build()

    @Provides
    internal fun provideMinifigDao(appDatabase: AppDatabase): MinifigDao =
        appDatabase.minifigDao()

    @Provides
    internal fun provideCategoryDao(appDatabase: AppDatabase): CategoryDao =
        appDatabase.categoryDao()

    @Singleton
    @Provides
    internal fun provideDBCoroutineDispatcher(): DBCoroutineDispatcher =
        object : DBCoroutineDispatcher {
            override fun get(): CoroutineContext = Dispatchers.IO
        }
}

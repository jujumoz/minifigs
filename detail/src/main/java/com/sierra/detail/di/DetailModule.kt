package com.sierra.detail.di

import android.content.Context
import android.content.Intent
import com.sierra.detail.ui.DetailActivity
import com.sierra.navigation.DetailNavigation
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [DetailNavigationModule::class]
)
abstract class DetailModule {

    @ContributesAndroidInjector
    internal abstract fun detailActivity(): DetailActivity
}

@Module
class DetailNavigationModule {

    @Provides
    fun provideDetailNavigation(): DetailNavigation = object : DetailNavigation {
        override fun createIntent(context: Context, minifigId: String): Intent =
            DetailActivity.createIntent(context, minifigId)
    }
}

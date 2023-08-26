package com.sierra.app.di

import androidx.lifecycle.ViewModelProvider
import com.sierra.common.ui.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

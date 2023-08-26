package com.sierra.app.di

import android.app.Application
import com.sierra.app.MyApplication
import com.sierra.common.di.DatabaseModule
import com.sierra.common.di.NetworkModule
import com.sierra.common.di.RepositoryModule
import com.sierra.detail.di.DetailModule
import com.sierra.main.di.MainModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DatabaseModule::class,
        DetailModule::class,
        MainModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}

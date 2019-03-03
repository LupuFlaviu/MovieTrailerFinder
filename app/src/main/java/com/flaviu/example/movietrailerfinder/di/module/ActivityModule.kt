package com.flaviu.example.movietrailerfinder.di.module

import androidx.lifecycle.ViewModel
import com.flaviu.example.movietrailerfinder.MainActivity
import com.flaviu.example.movietrailerfinder.di.scope.ActivityScope
import com.flaviu.example.movietrailerfinder.di.scope.ViewModelKey
import com.flaviu.example.movietrailerfinder.ui.main.model.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun provideMainActivityInjector(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainActivityViewModel(viewModel: MainViewModel): ViewModel
}
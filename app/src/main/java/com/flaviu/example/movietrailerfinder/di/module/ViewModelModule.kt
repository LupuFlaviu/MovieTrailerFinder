package com.flaviu.example.movietrailerfinder.di.module

import androidx.lifecycle.ViewModelProvider
import com.flaviu.example.movietrailerfinder.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
package com.flaviu.example.movietrailerfinder.di.module

import android.app.Application
import android.content.Context
import com.flaviu.example.movietrailerfinder.MovieTrailersApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideApplication(application: Application): MovieTrailersApp = application as MovieTrailersApp
}
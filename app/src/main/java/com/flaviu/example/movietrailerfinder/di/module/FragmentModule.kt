package com.flaviu.example.movietrailerfinder.di.module

import com.flaviu.example.movietrailerfinder.ui.main.GalleryFragment
import com.flaviu.example.movietrailerfinder.ui.main.MovieDetailFragment
import com.flaviu.example.movietrailerfinder.ui.main.MovieListFragment
import com.flaviu.example.movietrailerfinder.utils.ui.EmptyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun provideMovieListFragmentInjector(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun provideMovieDetailFragmentInjector(): MovieDetailFragment

    @ContributesAndroidInjector
    internal abstract fun provideEmptyFragmentInjector(): EmptyFragment

    @ContributesAndroidInjector
    internal abstract fun provideGalleryFragmentInjector(): GalleryFragment
}
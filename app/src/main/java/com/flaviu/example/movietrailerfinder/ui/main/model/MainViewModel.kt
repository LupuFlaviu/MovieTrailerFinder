package com.flaviu.example.movietrailerfinder.ui.main.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flaviu.example.movietrailerfinder.data.model.Movie
import com.flaviu.example.movietrailerfinder.data.repository.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    val movieList = MutableLiveData<List<Movie>>()

    fun getMovieList(): Observable<Result<List<Movie>>> {
        return moviesRepository.getMovieList()
    }
}
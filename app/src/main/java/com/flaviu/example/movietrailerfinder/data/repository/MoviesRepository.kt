package com.flaviu.example.movietrailerfinder.data.repository

import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.api.service.MovieService
import com.flaviu.example.movietrailerfinder.data.model.Movie
import io.reactivex.Observable
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MoviesRepository @Inject constructor(private val movieService: MovieService) {

    companion object {
        const val ERROR_TAG = "status_message"
    }

    fun getMovieList(): Observable<Result<List<Movie>>> {
        return movieService.getMovies(BuildConfig.MOVIE_API_KEY).map {
            if (it.isSuccessful) {
                Result.success(it.body()!!.movieList)
            } else {
                val jObjError = JSONObject(it.errorBody()!!.string())
                Result.failure(Throwable(jObjError.getString(ERROR_TAG)))
            }
        }.toObservable()
    }
}
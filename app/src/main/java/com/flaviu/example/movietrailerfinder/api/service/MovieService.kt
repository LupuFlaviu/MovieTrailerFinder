package com.flaviu.example.movietrailerfinder.api.service

import com.flaviu.example.movietrailerfinder.api.model.MovieList
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/top_rated")
    fun getMovies(@Query("api_key") apiKey: String): Single<Response<MovieList>>
}
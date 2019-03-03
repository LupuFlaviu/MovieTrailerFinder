package com.flaviu.example.movietrailerfinder.api.model

import com.flaviu.example.movietrailerfinder.data.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val movieList: List<Movie>
)
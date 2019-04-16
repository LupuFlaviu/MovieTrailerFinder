package com.flaviu.example.movietrailerfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val original_title: String,
    val overview: String,
    val vote_count: Int,
    val poster_path: String
) : Parcelable
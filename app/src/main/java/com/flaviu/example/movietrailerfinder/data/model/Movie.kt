package com.flaviu.example.movietrailerfinder.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: String,
    val title: String,
    val poster_path: String
) : Parcelable
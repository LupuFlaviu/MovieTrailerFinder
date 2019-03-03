package com.flaviu.example.movietrailerfinder.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.data.model.Movie
import com.flaviu.example.movietrailerfinder.di.module.GlideApp
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gallery_item.*

class GalleryAdapter(
    var movies: List<Movie>,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position], onMovieClicked)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(movie: Movie, onMovieClicked: (Movie) -> Unit) {
            GlideApp.with(image_cover).load(GlideUrl(BuildConfig.IMAGE_URL + movie.poster_path)).into(image_cover)
            text_title.text = movie.title
            itemContainer.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }
}
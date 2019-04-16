package com.flaviu.example.movietrailerfinder.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.data.model.Movie
import com.flaviu.example.movietrailerfinder.databinding.GalleryItemBinding
import com.flaviu.example.movietrailerfinder.di.module.GlideApp
import kotlinx.android.extensions.LayoutContainer

class GalleryAdapter(
    var movies: List<Movie>,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    private lateinit var binding: GalleryItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        binding = GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position], onMovieClicked)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(movie: Movie, onMovieClicked: (Movie) -> Unit) {
            GlideApp.with(binding.imageCover).load(GlideUrl(BuildConfig.IMAGE_URL + movie.poster_path))
                .into(binding.imageCover)
            binding.textTitle.text = movie.title
            binding.itemContainer.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }
}
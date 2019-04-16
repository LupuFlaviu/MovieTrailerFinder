package com.flaviu.example.movietrailerfinder.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.example.movietrailerfinder.data.model.Movie
import com.flaviu.example.movietrailerfinder.databinding.ListItemBinding
import kotlinx.android.extensions.LayoutContainer

class MoviesAdapter(
    var movies: List<Movie>,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position], onMovieClicked)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(movie: Movie, onMovieClicked: (Movie) -> Unit) {
            binding.textId.text = movie.id
            binding.textTitle.text = movie.title
            binding.itemContainer.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }
}
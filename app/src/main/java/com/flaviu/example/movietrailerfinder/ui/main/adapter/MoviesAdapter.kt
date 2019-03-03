package com.flaviu.example.movietrailerfinder.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.data.model.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class MoviesAdapter(
    var movies: List<Movie>,
    private val onMovieClicked: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position], onMovieClicked)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(movie: Movie, onMovieClicked: (Movie) -> Unit) {
            text_id.text = movie.id
            text_title.text = movie.title
            itemContainer.setOnClickListener {
                onMovieClicked(movie)
            }
        }
    }
}
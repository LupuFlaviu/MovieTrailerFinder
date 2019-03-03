package com.flaviu.example.movietrailerfinder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.ui.main.adapter.MoviesAdapter
import com.flaviu.example.movietrailerfinder.ui.main.model.MainViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*

class MovieListFragment : DaggerFragment() {

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java).movieList.observe(this, Observer {
            if (!::moviesAdapter.isInitialized) {
                moviesAdapter = MoviesAdapter(it) { movie ->
                    val action = MovieListFragmentDirections.actionMovieListDestToMovieDetailDest(movie)
                    findNavController().navigate(action)
                }
            } else {
                moviesAdapter.movies = it
                moviesAdapter.notifyDataSetChanged()
            }
            movie_list.adapter = moviesAdapter
        })
        movie_list.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        val decoration = DividerItemDecoration(context, VERTICAL)
        movie_list.addItemDecoration(decoration)
    }
}
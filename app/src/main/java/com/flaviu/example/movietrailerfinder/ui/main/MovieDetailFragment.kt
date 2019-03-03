package com.flaviu.example.movietrailerfinder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.di.module.GlideApp
import com.flaviu.example.movietrailerfinder.ui.main.adapter.TrailerAdapter
import com.flaviu.example.movietrailerfinder.utils.ui.AutofitGridLayoutManager
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.list_item.*

class MovieDetailFragment : DaggerFragment() {

    private lateinit var trailerAdapter: TrailerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = MovieListFragmentArgs.fromBundle(arguments!!).movie
        GlideApp.with(this).load(GlideUrl(BuildConfig.IMAGE_URL + movie.poster_path)).into(image_cover)
        text_title.text = movie.title
        text_id.text = movie.id
        if (!::trailerAdapter.isInitialized) {
            trailerAdapter = TrailerAdapter { url ->
                val action = MovieDetailFragmentDirections.actionMovieDetailDestToYoutubeDest(url)
                findNavController().navigate(action)
            }
        }
        trailer_list.adapter = trailerAdapter
        trailer_list.layoutManager = AutofitGridLayoutManager(
            context!!,
            resources.getDimensionPixelSize(R.dimen.thumbnail_size),
            RecyclerView.VERTICAL,
            false
        )
    }
}
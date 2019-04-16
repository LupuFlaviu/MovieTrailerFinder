package com.flaviu.example.movietrailerfinder.ui.main

import android.content.res.Configuration
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.model.GlideUrl
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.R
import com.flaviu.example.movietrailerfinder.databinding.FragmentDetailBinding
import com.flaviu.example.movietrailerfinder.di.module.GlideApp
import com.flaviu.example.movietrailerfinder.ui.main.adapter.TrailerAdapter
import com.flaviu.example.movietrailerfinder.utils.ui.AutofitGridLayoutManager
import dagger.android.support.DaggerFragment

class MovieDetailFragment : DaggerFragment() {

    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var autofitGridLayoutManager: AutofitGridLayoutManager
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = MovieListFragmentArgs.fromBundle(arguments!!).movie
        GlideApp.with(this).load(GlideUrl(BuildConfig.IMAGE_URL + movie.poster_path)).into(binding.imageCover)
        if (!::trailerAdapter.isInitialized) {
            trailerAdapter = TrailerAdapter { url ->
                val action = MovieDetailFragmentDirections.actionMovieDetailDestToYoutubeDest(url)
                findNavController().navigate(action)
            }
        }
        autofitGridLayoutManager = AutofitGridLayoutManager(
            context!!,
            resources.getDimensionPixelSize(R.dimen.thumbnail_size),
            RecyclerView.VERTICAL,
            false
        )
        binding.apply {
            trailerList.apply {
                layoutManager = autofitGridLayoutManager
                adapter = trailerAdapter
            }
            detailsContainer.apply {
                textTitle.text = movie.title
                labelTitle.typeface = Typeface.DEFAULT_BOLD
                textId.text = movie.id
                labelId.typeface = Typeface.DEFAULT_BOLD
            }
            textOriginalTitle.text = movie.original_title
            textVotes.text = movie.vote_count.toString()
            textDescription.text = movie.overview
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        binding.trailerList.apply {
            layoutManager = autofitGridLayoutManager
            invalidate()
        }
    }
}
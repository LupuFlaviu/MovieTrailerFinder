package com.flaviu.example.movietrailerfinder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.example.movietrailerfinder.databinding.FragmentGalleryBinding
import com.flaviu.example.movietrailerfinder.ui.main.adapter.GalleryAdapter
import com.flaviu.example.movietrailerfinder.ui.main.model.MainViewModel
import dagger.android.support.DaggerFragment

class GalleryFragment : DaggerFragment() {

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java).movieList.observe(this, Observer {
            if (!::galleryAdapter.isInitialized) {
                galleryAdapter = GalleryAdapter(it) { movie ->
                    val action = GalleryFragmentDirections.actionGalleryDestToMovieDetailDest(movie)
                    findNavController().navigate(action)
                }
            } else {
                galleryAdapter.movies = it
                galleryAdapter.notifyDataSetChanged()
            }
            binding.galleryList.adapter = galleryAdapter
        })
        binding.galleryList.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            false
        )
    }
}
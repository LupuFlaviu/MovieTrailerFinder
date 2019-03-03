package com.flaviu.example.movietrailerfinder.utils.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flaviu.example.movietrailerfinder.R
import dagger.android.support.DaggerFragment

class EmptyFragment : DaggerFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty, container, false)
    }
}
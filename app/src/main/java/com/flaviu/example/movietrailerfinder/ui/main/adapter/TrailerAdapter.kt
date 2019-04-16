package com.flaviu.example.movietrailerfinder.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flaviu.example.movietrailerfinder.BuildConfig
import com.flaviu.example.movietrailerfinder.databinding.TrailerItemBinding
import com.flaviu.example.movietrailerfinder.di.module.GlideApp
import kotlinx.android.extensions.LayoutContainer

class TrailerAdapter(private val onTrailerClicked: (String) -> Unit) :
    RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    companion object {
        const val THUMBNAIL_EXTENSION = "/hqdefault.jpg"
    }

    private val trailerList = arrayOf(
        "https://www.youtube.com/watch?v=PSoRx87OO6k",
        "https://www.youtube.com/watch?v=QkZxoko_HC0",
        "https://www.youtube.com/watch?v=WcXt9aUMbBk",
        "https://www.youtube.com/watch?v=tg52up16eq0",
        "https://www.youtube.com/watch?v=QAhcDHRZOak",
        "https://www.youtube.com/watch?v=qmxMAdV6s4U",
        "https://www.youtube.com/watch?v=9fbo_pQvU7M",
        "https://www.youtube.com/watch?v=nSbzyEJ8X9E",
        "https://www.youtube.com/watch?v=bILE5BEyhdo",
        "https://www.youtube.com/watch?v=jwSr1KnGPTo",
        "https://www.youtube.com/watch?v=Jlp94-C31cY",
        "https://www.youtube.com/watch?v=GVPA0hGgA5c",
        "https://www.youtube.com/watch?v=zvcm1yiyr98",
        "https://www.youtube.com/watch?v=KpMZXrRo99g",
        "https://www.youtube.com/watch?v=ZKsc2I4Tgsk",
        "https://www.youtube.com/watch?v=DzfpyUB60YY",
        "https://www.youtube.com/watch?v=h_RU7XGcYM0",
        "https://www.youtube.com/watch?v=Of52MNnXoxU",
        "https://www.youtube.com/watch?v=q8odfavYNzU",
        "https://www.youtube.com/watch?v=M7XM597XO94"
    )

    private lateinit var binding: TrailerItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        binding = TrailerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount() = trailerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(trailerList[position], onTrailerClicked)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindData(url: String, onTrailerClicked: (String) -> Unit) {
            GlideApp.with(binding.imageThumbnail)
                .load(
                    BuildConfig.YOUTUBE_URL + url.substring(
                        url.indexOf("=") + 1,
                        url.length
                    ) + THUMBNAIL_EXTENSION
                )
                .into(binding.imageThumbnail)
            binding.imageThumbnail.setOnClickListener {
                onTrailerClicked(url)
            }
        }
    }
}
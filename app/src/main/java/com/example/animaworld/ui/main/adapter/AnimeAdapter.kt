package com.example.animaworld.ui.main.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animaworld.data.model.Anime
import com.example.animaworld.databinding.ItemAnimeBinding
import com.example.animaworld.databinding.LoaderItemScrollBinding
import com.example.animaworld.utils.TYPE
import com.example.animaworld.utils.extensions.listen
import com.example.animaworld.utils.extensions.loadUrl

class AnimeAdapter(private val listener: OnAnimeSelectedListener? = null) :
    RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {

    private val animeList = mutableListOf<Anime>()
    private val loader by lazy { Anime().apply { viewType = TYPE.LOADER } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val itemBinding =
            ItemAnimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val loaderBinding =
            LoaderItemScrollBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return when (viewType) {
            TYPE.LOADER.ordinal -> AnimeViewHolder(loaderBinding)
            else -> AnimeViewHolder(itemBinding).listen { position, _ ->
                listener?.animeSelected(animeList[position])
            }
        }
    }

    override fun getItemCount() = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.load(animeList[position])
    }

    override fun getItemViewType(position: Int) = when (animeList[position].viewType) {
        TYPE.LOADER -> TYPE.LOADER.ordinal
        TYPE.ITEM -> TYPE.ITEM.ordinal
    }

    fun addLoader() {
        if (animeList.isNotEmpty())
            Handler(Looper.getMainLooper()).post {
                animeList.add(loader)
                notifyItemInserted(animeList.size - 1)
            }
    }

    fun removeLoader() {
        if (animeList.contains(loader)) {
            animeList.remove(loader)
            notifyItemRemoved(animeList.size)
        }
    }

    fun updateAnimeList(animeList: List<Anime>, clear: Boolean = false) {
        if (clear) this.animeList.clear()
        val positionStart = this.animeList.size
        this.animeList.addAll(animeList)
        notifyItemRangeInserted(positionStart, this.animeList.size)
    }

    class AnimeViewHolder(private val vBind: ViewBinding) : RecyclerView.ViewHolder(vBind.root) {

        fun load(anime: Anime) {
            when (vBind) {
                is ItemAnimeBinding -> vBind.apply {
                    val attrs = anime.attributes
                    val url =
                        attrs.posterImage.let { it.tiny ?: it.small ?: it.medium ?: it.original }
                            ?: attrs.coverImage?.let {
                                it.tiny ?: it.small ?: it.medium ?: it.original
                            }

                    ivImage.loadUrl(url)
                    tvTitle.text = attrs.titles.let { it.enUs ?: it.en ?: it.enJp } ?: attrs.slug
                }
                is LoaderItemScrollBinding -> {
                }
            }
        }
    }

    interface OnAnimeSelectedListener {
        fun animeSelected(anime: Anime)
    }
}
package com.example.animaworld.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animaworld.databinding.SnippetAnimeListingBinding
import com.example.animaworld.extensions.loadAnimeListing
import com.example.animaworld.model.Anime

class AnimeListingAdapter(): RecyclerView.Adapter<AnimeListingAdapter.AnimeListingHolder>() {

    private val animes = mutableListOf<Anime>()

    fun loadAnimes(animes: MutableList<Anime>){
        this.animes.clear()
        this.animes.addAll(animes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeListingHolder {
        SnippetAnimeListingBinding.inflate(LayoutInflater.from(parent.context),parent,false).let {
            return AnimeListingHolder(it)
        }
    }

    override fun getItemCount(): Int = animes.size

    override fun onBindViewHolder(holder: AnimeListingHolder, position: Int) {
        holder.loadAnime(animes[position])
    }

    class AnimeListingHolder(private val binding: SnippetAnimeListingBinding): RecyclerView.ViewHolder(binding.root) {

        fun loadAnime(anime: Anime) {
            binding.tvAnimeTitle.text = anime.attributes.titles.en
            binding.ivAnimePic.loadAnimeListing(anime.attributes.posterImage.large)
        }
    }
}
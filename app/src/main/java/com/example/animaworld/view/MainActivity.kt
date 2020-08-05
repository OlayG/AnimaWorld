package com.example.animaworld.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animaworld.R
import com.example.animaworld.adapters.AnimeListingAdapter
import com.example.animaworld.databinding.ActivityMainBinding
import com.example.animaworld.model.Anime
import com.example.animaworld.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val vm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //inflate activity
        ActivityMainBinding.inflate(LayoutInflater.from(this)).let {
            binding = it
            setContentView(it.root)
        }

        binding.rvAnimeListings.let {
            it.layoutManager = GridLayoutManager(this,2)
            it.adapter = AnimeListingAdapter()
        }

        vm.fetchAnime()
        vm.getAnimeResponse.observe(this, Observer { AnimeResponse ->
            binding.rvAnimeListings.apply {
                (this.adapter as AnimeListingAdapter).loadAnimes(AnimeResponse.data as MutableList<Anime>)
            }
        })
    }
}
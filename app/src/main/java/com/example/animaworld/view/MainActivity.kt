package com.example.animaworld.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.animaworld.R
import com.example.animaworld.adapters.AnimeListingAdapter
import com.example.animaworld.databinding.ActivityMainBinding
import com.example.animaworld.model.Anime
import com.example.animaworld.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.snippet_top_app_bar.view.*

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
        initRecycler()
        vm.fetchAnime()
        initAnimeObserver()
        initSearchBar()
    }

    private fun initRecycler(){
        binding.rvAnimeListings.let {
            it.layoutManager = GridLayoutManager(this,2)
            it.adapter = AnimeListingAdapter()
        }
    }

    private fun initAnimeObserver(){
        vm.getAnimeResponse.observe(this, Observer { AnimeResponse ->
            binding.rvAnimeListings.apply {
                (this.adapter as AnimeListingAdapter).loadAnimes(AnimeResponse.data as MutableList<Anime>)
            }
        })
    }

    private fun initSearchBar() {
        binding.incTopAppBar.tbTopToolBar.menu.findItem(R.id.mi_top_search).also{ searchItem ->
            (searchItem.actionView as SearchView).let{searchView->
                searchView.setOnCloseListener {
                    println("query close")
                    TODO("Not sure if this needs to be a thing")
                    return@setOnCloseListener false
                }

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        println("query submit")
                        TODO("Needs to execute the search")
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        println("query changed")
                        TODO("Needs to bring up suggestions of things that can be searched")
                        return true
                    }

                })
            }
        }
    }
}
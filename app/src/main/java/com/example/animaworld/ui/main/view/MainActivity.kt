package com.example.animaworld.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.animaworld.data.api.KitsuServiceImpl
import com.example.animaworld.data.model.Anime
import com.example.animaworld.data.repository.KitsuRepo
import com.example.animaworld.databinding.ActivityMainBinding
import com.example.animaworld.ui.main.adapter.AnimeAdapter
import com.example.animaworld.ui.main.viewmodel.MainViewModel
import com.example.animaworld.utils.*
import com.example.animaworld.utils.extensions.show

// This is a class, extends AppCompatActivity and implements OnAnimeSelectedListener & OnLoadMoreListener
class MainActivity : AppCompatActivity(), AnimeAdapter.OnAnimeSelectedListener, OnLoadMoreListener {

    // This is a declaration of factory which is type MainViewModelFactory
    // It is initialized using KitsuRepo
    private val factory by lazy { MainViewModelFactory(KitsuRepo(KitsuServiceImpl())) }

    private val vm by viewModels<MainViewModel> { factory }

    private lateinit var binding: ActivityMainBinding

    private val animeAdapter by lazy { AnimeAdapter(this) }

    private val gridLayoutManager by lazy {
        GridLayoutManager(this, 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (animeAdapter.getItemViewType(position)) {
                        TYPE.ITEM.ordinal -> 1
                        else -> 2
                    }
                }
            }
        }
    }

    private val scrollListener by lazy {
        RecyclerViewLoadMoreScroll(gridLayoutManager).apply {
            setOnLoadMoreListener(this@MainActivity)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(LayoutInflater.from(this)).also {
            binding = it
            setContentView(it.root)
        }

        initRV()
        setUpObservers()
    }

    private fun initRV() {
        binding.rvAnime.apply {
            layoutManager = gridLayoutManager
            adapter = animeAdapter
            setHasFixedSize(true)
            addOnScrollListener(scrollListener)
        }
    }

    private fun setUpObservers() {
        vm.animeObservable.observe(this, Observer { resource ->
            when (resource.status) {
                Status.LOADING -> animeAdapter.addLoader()
                Status.SUCCESS -> {
                    resource.data?.animeList?.let { list -> animeAdapter.updateAnimeList(list) }
                    binding.loader.root.show(show = false)
                    animeAdapter.removeLoader()
                    scrollListener.setLoaded()
                }
                Status.ERROR -> {
                    // show some error dialog
                }
            }
        })
    }

    override fun animeSelected(anime: Anime) {
        Toast.makeText(this@MainActivity, anime.attributes.titles.en, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onLoadMore() {
        vm.load(MainViewModel.LOAD.NEXT)
    }
}
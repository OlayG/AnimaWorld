package com.example.animaworld.view

import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import android.widget.Switch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animaworld.R
import com.example.animaworld.adapters.AnimeListingAdapter
import com.example.animaworld.databinding.ActivityMainBinding
import com.example.animaworld.model.Anime
import com.example.animaworld.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val vm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private var page: Int = 0
    private var limit: Int = 20
    private var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is deprecated but cannot find a working work around using insets

        //inflate activity
        ActivityMainBinding.inflate(LayoutInflater.from(this)).let {
            binding = it
            setContentView(it.root)
        }
        initDrawer()
        initRecycler()
        vm.fetchAnimeCoroutines(limit, page)
        initAnimeObserver()
        initSearchBar()
    }


    private fun initRecycler() {
        binding.incAnimeScrollView.rvAnimeListings.let {
            it.layoutManager = GridLayoutManager(this, 2)
            it.adapter = AnimeListingAdapter()
            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                    val totalItemCount = recyclerView.layoutManager?.itemCount
                    val pastVisibleItems =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    var query: String = ""
                    if ((visibleItemCount + pastVisibleItems) == totalItemCount && !isLoading) {
                        binding.incAnimeScrollView.incTopAppBar.tbTopToolBar.menu.findItem(R.id.mi_top_search)
                            .also { searchItem ->
                                query = (searchItem.actionView as SearchView).query.toString()
                            }
                        isLoading = true
                        page += 1
                        if (query != "") {
                            vm.fetchAnimeCoroutines(limit, page, query)
                        } else {
                            vm.fetchAnimeCoroutines(limit, page)
                        }

                    }
                }
            })
        }
    }

    private fun initAnimeObserver() {
        vm.getAnimeResponse.observe(this, Observer { AnimeResponse ->
            binding.incAnimeScrollView.rvAnimeListings.apply {
                isLoading = if (page == 0) {
                    (this.adapter as AnimeListingAdapter).loadAnimes(AnimeResponse.data as MutableList<Anime>)
                    false
                } else {
                    (this.adapter as AnimeListingAdapter).addAnimes(AnimeResponse.data as MutableList<Anime>)
                    false
                }

            }
        })
    }

    private fun initSearchBar() {
        binding.incAnimeScrollView.incTopAppBar.tbTopToolBar.menu.findItem(R.id.mi_top_search)
            .also { searchItem ->
                (searchItem.actionView as SearchView).let { searchView ->
                    searchView.setOnCloseListener {
                        isLoading = true
                        page = 0
                        vm.fetchAnimeCoroutines(limit, page)
                        return@setOnCloseListener false
                    }

                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            if (query != null) {
                                isLoading = true
                                page = 0
                                vm.fetchAnimeCoroutines(limit, page, query)
                            }
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
//                        TODO("Needs to bring up suggestions of things that can be searched")
                            return true
                        }

                    })
                }
            }
    }

    private fun initDrawer(){
        binding.incAnimeScrollView.incTopAppBar.tbTopToolBar.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(binding.navigation)
        }

        binding.navigation.menu.findItem(R.id.tgl_dark_mode).let{menuItem->
            (menuItem.actionView as Switch).setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, b ->
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            })
        }
//        MenuItem menuItem = binding.navigation.getMenu().findItem(R.id.tgl_dark_mode);
//        ((Switch) menuItem.getActionView()).setOnCheckedChangeListener(this);
    }

}
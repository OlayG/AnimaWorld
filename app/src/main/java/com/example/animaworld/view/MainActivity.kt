package com.example.animaworld.view

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.core.view.doOnLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.animaworld.R
import com.example.animaworld.adapters.AnimeListingAdapter
import com.example.animaworld.databinding.ActivityMainBinding
import com.example.animaworld.model.Anime
import com.example.animaworld.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.snippet_top_app_bar.view.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity()  {
    private val vm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private var page: Int = 0
    private var isLoading: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is deprecated but cannot find a working work around using insets

        //inflate activity
        ActivityMainBinding.inflate(LayoutInflater.from(this)).let {
            binding = it
            setContentView(it.root)
        }
        initRecycler()
        vm.fetchAnimeCoroutines(20,page)
        initAnimeObserver()
        initSearchBar()
    }


    private fun initRecycler(){
        binding.rvAnimeListings.let {
            it.layoutManager = GridLayoutManager(this,2)
            it.adapter = AnimeListingAdapter()
            it.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                }

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
                    val totalItemCount = recyclerView.layoutManager?.itemCount
                    val pastVisibleItems = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
//                    println("visibleItemCount: $visibleItemCount")
//                    println("totalItemCount: $totalItemCount")
//                    println("pastVisibleItems: $pastVisibleItems")
                    if((visibleItemCount + pastVisibleItems) == totalItemCount && !isLoading){
                        isLoading = true
                        page = page.inc()
                        vm.fetchAnimeCoroutines(20,page)
                    }
                }
            })
        }
    }

    private fun initAnimeObserver(){
        vm.getAnimeResponse.observe(this, Observer { AnimeResponse ->
            binding.rvAnimeListings.apply {
                if(page == 0 ){
                    (this.adapter as AnimeListingAdapter).loadAnimes(AnimeResponse.data as MutableList<Anime>)
                }else{
                    (this.adapter as AnimeListingAdapter).addAnimes(AnimeResponse.data as MutableList<Anime>)
                    isLoading = false
                }

            }
        })
    }

    private fun initSearchBar() {
        binding.incTopAppBar.tbTopToolBar.menu.findItem(R.id.mi_top_search).also{ searchItem ->
            (searchItem.actionView as SearchView).let{searchView->
                searchView.setOnCloseListener {
                    println("query close")
//                    TODO("Not sure if this needs to be a thing")
                    return@setOnCloseListener false
                }

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        println("query submit")
//                        TODO("Needs to execute the search")
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        println("query changed")
//                        TODO("Needs to bring up suggestions of things that can be searched")
                        return true
                    }

                })
            }
        }
    }

}
package com.example.animaworld.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaworld.data.model.AnimeResponse
import com.example.animaworld.data.repository.KitsuRepo
import com.example.animaworld.utils.Resource
import com.example.animaworld.utils.extensions.successNoData
import com.example.animaworld.utils.extensions.successWithData
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val kitsuRepo: KitsuRepo) : ViewModel() {
    private val _animeResponse = MutableLiveData<Resource<AnimeResponse>>()
    val animeObservable: LiveData<Resource<AnimeResponse>>
        get() = _animeResponse

    init {
        load()
    }

    private fun fetchAnime(url: String? = null) {
        setStateToLoading(_animeResponse)
        viewModelScope.launch {
            val response = if (url.isNullOrEmpty())
                kitsuRepo.getAnime()
            else
                kitsuRepo.getAnime(url)

            handleResponse(response)
        }
    }

    private fun handleResponse(response: Response<AnimeResponse>) {
        val resource = when {
            response.successWithData() -> Resource.success(response.body())
            response.successNoData() -> Resource.error("No data returned")
            else -> Resource.error("Something went wrong: ${response.message()}")
        }
        _animeResponse.postValue(resource)
    }

    fun load(loadOption: LOAD? = null) {
        _animeResponse.value?.data?.let { animeResponse ->
            val link = when (loadOption) {
                LOAD.FIRST -> animeResponse.links.first
                LOAD.NEXT -> animeResponse.links.next
                LOAD.PREV -> animeResponse.links.prev
                LOAD.LAST -> animeResponse.links.last
                else -> null
            }
            fetchAnime(link)
        } ?: fetchAnime()
    }

    private fun <T> setStateToLoading(mutableLiveData: MutableLiveData<Resource<T>>) {
        mutableLiveData.value = Resource.loading()
    }

    enum class LOAD {
        FIRST,
        NEXT,
        PREV,
        LAST
    }
}
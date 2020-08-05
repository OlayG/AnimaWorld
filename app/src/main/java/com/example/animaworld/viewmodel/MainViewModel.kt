package com.example.animaworld.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animaworld.model.AnimeResponse
import com.example.animaworld.repository.KitsuRepo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val kitsuRepo = KitsuRepo()
    private val _animeResponse = MutableLiveData<AnimeResponse>()
    val getAnimeResponse: LiveData<AnimeResponse>
        get() = _animeResponse

    fun fetchAnime() {
        kitsuRepo.getAnime().enqueue(object : Callback<AnimeResponse> {
            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<AnimeResponse>, response: Response<AnimeResponse>) {
                response.body()?.let { animeResponse ->
                    _animeResponse.value = animeResponse
                }
            }

        })
    }

    fun fetchAnimeCoroutines() {
        viewModelScope.launch {
            kitsuRepo.getAnimeCoroutines().let { animalResponse ->
                _animeResponse.value = animalResponse
            }
        }
    }

}
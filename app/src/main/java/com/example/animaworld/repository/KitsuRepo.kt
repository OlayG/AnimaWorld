package com.example.animaworld.repository

import com.example.animaworld.model.AnimeResponse
import com.example.animaworld.repository.remote.RetrofitInstance
import retrofit2.Call

class KitsuRepo {

    fun getAnime(): Call<AnimeResponse> = RetrofitInstance.kitsuService.getAnimeRetrofit()

    suspend fun getAnimeCoroutines() : AnimeResponse = RetrofitInstance.kitsuService.getAnimeRetrofitCoroutines()
}
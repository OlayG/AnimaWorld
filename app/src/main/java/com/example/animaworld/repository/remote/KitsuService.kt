package com.example.animaworld.repository.remote

import com.example.animaworld.model.AnimeResponse
import retrofit2.Call
import retrofit2.http.GET

interface KitsuService {

    // Using Default Retrofit Threading
    @GET("anime")
    fun getAnimeRetrofit() : Call<AnimeResponse>

    // Using Coroutines for Threading
    @GET("anime")
    suspend fun getAnimeRetrofitCoroutines() : AnimeResponse
}
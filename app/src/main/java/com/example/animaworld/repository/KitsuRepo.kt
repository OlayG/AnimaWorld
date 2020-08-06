package com.example.animaworld.repository

import com.example.animaworld.model.Anime
import com.example.animaworld.model.AnimeResponse
import com.example.animaworld.repository.remote.RetrofitInstance
import retrofit2.Call

class KitsuRepo {

    fun getAnime(): Call<AnimeResponse> = RetrofitInstance.kitsuService.getAnimeRetrofit()

    suspend fun getAnimeCoroutines(limit: Int, page: Int) : AnimeResponse{
        val offset = limit * page
        return RetrofitInstance.kitsuService.getAnimeRetrofitCoroutines(limit,offset)
    }

    suspend fun getAnimeCoroutines() : AnimeResponse = RetrofitInstance.kitsuService.getAnimeRetrofitCoroutines()


}
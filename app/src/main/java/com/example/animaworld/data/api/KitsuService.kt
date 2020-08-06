package com.example.animaworld.data.api

import com.example.animaworld.data.model.AnimeResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface KitsuService {

    @GET("anime")
    suspend fun getAnime(): Response<AnimeResponse>

    @GET
    suspend fun getAnime(@Url url: String): Response<AnimeResponse>
}
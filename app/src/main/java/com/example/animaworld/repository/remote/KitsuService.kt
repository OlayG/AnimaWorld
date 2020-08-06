package com.example.animaworld.repository.remote

import com.example.animaworld.model.AnimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface KitsuService {

    // Using Default Retrofit Threading
    @GET("anime")
    fun getAnimeRetrofit(): Call<AnimeResponse>

    // Using Coroutines for Threading
    @GET("anime")
    suspend fun getAnimeRetrofitCoroutines(): AnimeResponse

    //pagination
    @GET("anime")
    suspend fun getAnimeRetrofitCoroutines(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int
    ): AnimeResponse

    //query
    @GET("anime")
    suspend fun getAnimeRetrofitCoroutines(
        @Query("page[limit]") limit: Int,
        @Query("page[offset]") offset: Int,
        @Query("filter[text]") q: String
    ) : AnimeResponse

}
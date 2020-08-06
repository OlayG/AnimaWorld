package com.example.animaworld.utils.extensions

import com.example.animaworld.data.model.AnimeResponse
import retrofit2.Response

fun Response<AnimeResponse>.successWithData() =
    isSuccessful && body() != null && !body()?.animeList.isNullOrEmpty()

fun Response<AnimeResponse>.successNoData() =
    isSuccessful && body() != null && body()?.animeList.isNullOrEmpty()




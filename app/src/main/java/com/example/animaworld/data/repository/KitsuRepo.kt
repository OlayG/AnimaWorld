package com.example.animaworld.data.repository

import com.example.animaworld.data.api.KitsuServiceImpl
import com.example.animaworld.data.model.AnimeResponse
import retrofit2.Response

class KitsuRepo(private val kitsuService: KitsuServiceImpl) {

    suspend fun getAnime(): Response<AnimeResponse> = kitsuService.getAnime()
    suspend fun getAnime(url: String): Response<AnimeResponse> = kitsuService.getAnime(url)
}
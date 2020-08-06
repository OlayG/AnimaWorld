package com.example.animaworld.data.api

class KitsuServiceImpl : KitsuService {

    override suspend fun getAnime() = RetrofitInstance.kitsuService.getAnime()

    override suspend fun getAnime(url: String) = RetrofitInstance.kitsuService.getAnime(url)
}
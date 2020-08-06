package com.example.animaworld.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val client by lazy {
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
            .let { OkHttpClient.Builder().addInterceptor(it).build() }
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    val kitsuService: KitsuService by lazy { retrofit.create(
        KitsuService::class.java) }
}
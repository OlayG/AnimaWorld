package com.example.animaworld.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AnimeResponse(
    @Json(name = "data")
    val animeList: List<Anime> = listOf(),
    val meta: Meta = Meta(),
    val links: Links = Links()
) : Parcelable
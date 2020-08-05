package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AnimeResponse(
    val data: List<Anime> = listOf(),
    val meta: Meta = Meta(),
    val links: Links = Links()
) : Parcelable
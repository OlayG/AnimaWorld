package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Titles(
    val en: String? = null,
    @Json(name = "en_jp")
    val enJp: String? = null,
    @Json(name = "ja_jp")
    val jaJp: String? = null,
    @Json(name = "en_us")
    val enUs: String? = null
) : Parcelable
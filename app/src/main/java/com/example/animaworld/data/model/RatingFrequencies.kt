package com.example.animaworld.data.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RatingFrequencies(
    @Json(name = "2")
    val x2: String = "",
    @Json(name = "3")
    val x3: String = "",
    @Json(name = "4")
    val x4: String = "",
    @Json(name = "5")
    val x5: String = "",
    @Json(name = "6")
    val x6: String = "",
    @Json(name = "7")
    val x7: String = "",
    @Json(name = "8")
    val x8: String = "",
    @Json(name = "9")
    val x9: String = "",
    @Json(name = "10")
    val x10: String = "",
    @Json(name = "11")
    val x11: String = "",
    @Json(name = "12")
    val x12: String = "",
    @Json(name = "13")
    val x13: String = "",
    @Json(name = "14")
    val x14: String = "",
    @Json(name = "15")
    val x15: String = "",
    @Json(name = "16")
    val x16: String = "",
    @Json(name = "17")
    val x17: String = "",
    @Json(name = "18")
    val x18: String = "",
    @Json(name = "19")
    val x19: String = "",
    @Json(name = "20")
    val x20: String = ""
) : Parcelable
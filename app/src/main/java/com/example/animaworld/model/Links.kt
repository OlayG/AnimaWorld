package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Links(
    val first: String = "",
    val prev: String = "",
    val next: String = "",
    val last: String = ""
) : Parcelable
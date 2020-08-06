package com.example.animaworld.data.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Links(
    val first: String? = null,
    val prev: String? = null,
    val next: String? = null,
    val last: String? = null
) : Parcelable
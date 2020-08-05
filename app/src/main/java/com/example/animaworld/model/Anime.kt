package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Anime(
    val id: String = "",
    val type: String = "",
    val links: Links = Links(),
    val attributes: Attributes = Attributes(),
    val relationships: Relationships = Relationships()
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Links(
        val self: String = ""
    ) : Parcelable
}
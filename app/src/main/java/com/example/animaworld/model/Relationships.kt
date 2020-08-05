package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Relationships(
    val genres: Data = Data(),
    val categories: Data = Data(),
    val castings: Data = Data(),
    val installments: Data = Data(),
    val mappings: Data = Data(),
    val reviews: Data = Data(),
    val mediaRelationships: Data = Data(),
    val characters: Data = Data(),
    val staff: Data = Data(),
    val productions: Data = Data(),
    val quotes: Data = Data(),
    val episodes: Data = Data(),
    val streamingLinks: Data = Data(),
    val animeProductions: Data = Data(),
    val animeCharacters: Data = Data(),
    val animeStaff: Data = Data()
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Data(
        val links: Links = Links()
    ) : Parcelable {

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Links(
            val self: String? = null,
            val related: String? = null
        ) : Parcelable
    }


}
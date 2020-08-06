package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@JsonClass(generateAdapter = true)
data class Attributes(
    val createdAt: String = "",
    val updatedAt: String = "",
    val slug: String = "",
    val synopsis: String = "",
    val coverImageTopOffset: Int? = null,
    val titles: Titles = Titles(),
    val canonicalTitle: String? = null,
    val abbreviatedTitles: List<String> = listOf(),
    val averageRating: String? = null,
    val ratingFrequencies: RatingFrequencies = RatingFrequencies(),
    val userCount: Int? = null,
    val favoritesCount: Int? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val nextRelease: @RawValue Any? = null,
    val popularityRank: Int? = null,
    val ratingRank: Int? = null,
    val ageRating: String? = null,
    val ageRatingGuide: String? = null,
    val subtype: String? = null,
    val status: String? = null,
    val tba: String? = null,
    val posterImage: Image = Image(),
    val coverImage: Image? = null,
    val episodeCount: Int? = null,
    val episodeLength: Int? = null,
    val totalLength: Int? = null,
    val youtubeVideoId: String? = null,
    val showType: ShowType? = null,
    val nsfw: Boolean = false
) : Parcelable {

    enum class ShowType {
        @Json(name = "ONA")
        ONA,

        @Json(name = "OVA")
        OVA,

        @Json(name = "TV")
        TV,

        @Json(name = "movie")
        MOVIE,

        @Json(name = "music")
        MUSIC,

        @Json(name = "special")
        SPECIAL
    }
}
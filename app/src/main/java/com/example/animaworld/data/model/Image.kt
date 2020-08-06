package com.example.animaworld.data.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    val tiny: String? = null,
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null,
    val original: String? = null,
    val meta: Meta = Meta()
) : Parcelable {

    @Parcelize
    @JsonClass(generateAdapter = true)
    data class Meta(
        val dimensions: Dimensions = Dimensions()
    ) : Parcelable {

        @Parcelize
        @JsonClass(generateAdapter = true)
        data class Dimensions(
            val tiny: Size? = null,
            val small: Size? = null,
            val medium: Size? = null,
            val large: Size? = null
        ) : Parcelable {

            @Parcelize
            @JsonClass(generateAdapter = true)
            data class Size(
                val width: String? = null,
                val height: String? = null
            ) : Parcelable
        }
    }
}
package com.example.animaworld.model


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(
    val tiny: String = "",
    val small: String = "",
    val medium: String = "",
    val large: String = "",
    val original: String = "",
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
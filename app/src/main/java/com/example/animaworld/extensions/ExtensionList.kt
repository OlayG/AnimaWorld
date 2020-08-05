package com.example.animaworld.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

fun ImageView.loadAnimeListing(url:String){

    RoundedCornersTransformation(100,0).let {
        Glide.with(this).load(url).apply(RequestOptions.bitmapTransform(it)).into(this)
    }
}
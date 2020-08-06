package com.example.animaworld.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation


fun ImageView.loadAnimeListing(url:String){

    RequestOptions.fitCenterTransform().transform(RoundedCorners(100)).diskCacheStrategy(
        DiskCacheStrategy.ALL).let{
        Glide.with(this).load(url).apply(it).into(this)
    }
}
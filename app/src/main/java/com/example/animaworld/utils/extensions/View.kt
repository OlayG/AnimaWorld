package com.example.animaworld.utils.extensions

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.animaworld.R

fun View.show(show: Boolean? = true) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}

fun ImageView.loadUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .fallback(ColorDrawable(ContextCompat.getColor(context, R.color.gray)))
        .apply(RequestOptions().override(measuredWidth, measuredHeight))
        .into(this)
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(adapterPosition, itemViewType)
    }
    return this
}
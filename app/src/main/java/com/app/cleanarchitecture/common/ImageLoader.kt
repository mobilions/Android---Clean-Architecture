package com.app.cleanarchitecture.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageLoader {

    fun loadImage(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    fun loadImageWithPlaceholder(
        url: String,
        imageView: ImageView,
        placeholder: Int,
        errorPlaceholder: Int
    ) {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .error(errorPlaceholder)
            .into(imageView)
    }
}
package com.ikiugu.kitty.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.ikiugu.kitty.R


/**
 * Created by Alfred Ikiugu on 29/08/2021
 */


/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    if (url != null) {
        Glide
            .with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_home)
            .error(R.drawable.side_nav_bar)
            .transition(withCrossFade())
            .into(imageView)

    }
}
  
package com.ikiugu.kitty.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
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
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        Glide
            .with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_home)
            .error(R.drawable.side_nav_bar)
            .transition(withCrossFade(factory))
            .into(imageView)

    }
}


@BindingAdapter("url", "loader")
fun setImageSrcFromUrlWithLoader(view: ImageView, url: String?, loader: LottieAnimationView) {
    url?.let {
        Glide.with(view)
            .load(it)
            .addListener(imageLoadingListener(loader))
            .centerCrop()
            .into(view)
    }
}

fun imageLoadingListener(pendingImage: LottieAnimationView): RequestListener<Drawable?>? {
    return object : RequestListener<Drawable?> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: com.bumptech.glide.request.target.Target<Drawable?>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            pendingImage.pauseAnimation()
            pendingImage.visibility = View.GONE
            return false
        }
    }
}
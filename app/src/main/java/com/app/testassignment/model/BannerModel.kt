package com.app.testassignment.model

import android.R
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.app.testassignment.GlideApp
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BannerModel {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: AppCompatImageView, imageURL: String) {
            Log.e("imsgeurl", imageURL)
            val options = RequestOptions()
            options.override(200, 150)
            options.diskCacheStrategy(DiskCacheStrategy.ALL)
            GlideApp.with(imageView.context)
                .setDefaultRequestOptions(options)
                .load(imageURL)
                .into(imageView)
        }

    }
}
package com.app.testassignment.model

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ProductModel {
    @SerializedName("productId")
    @Expose
    var productId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("localPrice")
    @Expose
    var localPrice: Int? = null

    @SerializedName("imgUrl")
    @Expose
    var imgUrl: String? = null

    @SerializedName("rank")
    @Expose
    var rank: Int? = null

    @SerializedName("ratingEmoji")
    @Expose
    var ratingEmoji: String? = null

    @SerializedName("localCrossedPrice")
    @Expose
    var localCrossedPrice: Int? = null

    @SerializedName("brand")
    @Expose
    var brand: String? = null

    companion object  {
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun loadImage(imageView: AppCompatImageView, imageURL: String) {
            Log.e("imsgeurl",imageURL)
            Glide.with(imageView.context)
                .load(imageURL)
                .into(imageView)
        }
    }

}
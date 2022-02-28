package com.app.testassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.testassignment.R
import com.app.testassignment.databinding.ItemBannerBinding
import com.app.testassignment.model.BannerModel

class BannerAdapter : RecyclerView.Adapter<CommonViewHolder>() {
    private var mBannerModelList: ArrayList<BannerModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val mBannerListItemBinding = DataBindingUtil.inflate<ItemBannerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_banner, parent, false
        )
        return CommonViewHolder(mBannerListItemBinding)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val currentBanner = mBannerModelList!![position]
        (holder.binding as ItemBannerBinding).bannerModel = currentBanner
    }

    override fun getItemCount(): Int {
        return mBannerModelList?.count() ?: run { 0 }
    }
    fun setBannerList(mBannerList: ArrayList<BannerModel>) {
        this.mBannerModelList = mBannerList
        notifyDataSetChanged()
    }
}
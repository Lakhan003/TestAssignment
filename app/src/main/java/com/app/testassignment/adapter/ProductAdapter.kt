package com.app.testassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.testassignment.R
import com.app.testassignment.databinding.ItemBannerBinding
import com.app.testassignment.databinding.ItemProductBinding
import com.app.testassignment.model.ProductModel

class ProductAdapter : RecyclerView.Adapter<CommonViewHolder>() {
    private var mProductModelList: ArrayList<ProductModel>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonViewHolder {
        val mProductListItemBinding = DataBindingUtil.inflate<ItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product, parent, false
        )
        return CommonViewHolder(mProductListItemBinding)
    }

    override fun onBindViewHolder(holder: CommonViewHolder, position: Int) {
        val currentProduct = mProductModelList!![position]
        (holder.binding as ItemProductBinding).productModel = currentProduct
    }

    override fun getItemCount(): Int {
        return mProductModelList?.count() ?: run { 0 }
    }

    fun setProductList(mProductList: ArrayList<ProductModel>) {
        mProductModelList?.let {
            it.addAll(mProductList)
            val pos = it.count()
            notifyItemRangeInserted(pos - mProductList.count() - 1, pos - 1)
        } ?: run {
            mProductModelList = ArrayList()
            mProductModelList!!.addAll(mProductList)
            notifyDataSetChanged()
        }
    }
}
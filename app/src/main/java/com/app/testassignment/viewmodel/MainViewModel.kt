package com.app.testassignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.app.testassignment.model.BannerModel
import com.app.testassignment.model.ProductModel
import com.app.testassignment.repository.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val mMainRepository: MainRepository = MainRepository()

    val allBanner: LiveData<List<BannerModel>>
        get() = mMainRepository.getMutableLiveData()

    fun allProduct(index :Int):LiveData<List<ProductModel>>{
        return mMainRepository.getProductMutableLiveData(index)
    }
}
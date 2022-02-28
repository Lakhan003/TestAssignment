package com.app.testassignment.repository

import androidx.lifecycle.MutableLiveData
import com.app.testassignment.model.BannerModel
import com.app.testassignment.model.ProductModel
import com.app.testassignment.networkcommunication.RetrofitClient
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    private val mmBannerModelmist = ArrayList<BannerModel>()
    private val mutableLiveData = MutableLiveData<List<BannerModel>>()
    private val mProductModelmist = ArrayList<ProductModel>()
    private val mutableProductLiveData = MutableLiveData<List<ProductModel>>()

    fun getMutableLiveData(): MutableLiveData<List<BannerModel>> {
        ///ini Retrofit Class
        val userDataService = RetrofitClient.service
        ///call the API that define In Interface
        val call = userDataService.apiRequestBanner
        call.enqueue(object : Callback<ResponseBody> {
            var message: String? = null
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                val gson = GsonBuilder().create()
                if (response.body() != null) {
                    val json = JsonParser().parse(response.body()!!.string()).asJsonObject
                    //Log.e("data",json.toString())
                    if (json != null) {
                        if (json.get("Message").asString == "SUCCESS") {
                            val jsonData = json.getAsJsonObject("Data")
                            val jsonmainBanner = jsonData.getAsJsonArray("mainBanner")
                            if (jsonmainBanner != null) {
                                for (i in 0 until jsonmainBanner.size()) {
                                    try {
                                        val jsonobj =
                                            JsonParser().parse(
                                                jsonmainBanner.get(i).toString()
                                            ).asJsonObject
                                        val responseModel =
                                            gson.fromJson(jsonobj, BannerModel::class.java)
                                        mmBannerModelmist.add(responseModel)
                                    } catch (ex: Exception) {

                                    }
                                }
                            }
                            val jsonbrandZoneBanner = jsonData.getAsJsonArray("brandZoneBanner")
                            if (jsonbrandZoneBanner != null) {
                                for (i in 0 until jsonbrandZoneBanner.size()) {
                                    try {
                                        val jsonobj =
                                            JsonParser().parse(
                                                jsonbrandZoneBanner.get(i).toString()
                                            ).asJsonObject
                                        val responseModel =
                                            gson.fromJson(jsonobj, BannerModel::class.java)
                                        mmBannerModelmist.add(responseModel)
                                    } catch (ex: Exception) {

                                    }
                                }
                            }
                            val jsonpromotionalBanner = jsonData.getAsJsonArray("promotionalBanner")
                            if (jsonpromotionalBanner != null) {
                                for (i in 0 until jsonpromotionalBanner.size()) {
                                    try {
                                        val jsonobj =
                                            JsonParser().parse(
                                                jsonpromotionalBanner.get(i).toString()
                                            ).asJsonObject
                                        val responseModel =
                                            gson.fromJson(jsonobj, BannerModel::class.java)
                                        mmBannerModelmist.add(responseModel)
                                    } catch (ex: Exception) {

                                    }
                                }
                            }
                            val jsonpromotionalBanner2 =
                                jsonData.getAsJsonArray("promotionalBanner2")
                            if (jsonpromotionalBanner2 != null) {
                                for (i in 0 until jsonpromotionalBanner2.size()) {
                                    try {
                                        val jsonobj =
                                            JsonParser().parse(
                                                jsonpromotionalBanner2.get(i).toString()
                                            ).asJsonObject
                                        val responseModel =
                                            gson.fromJson(jsonobj, BannerModel::class.java)
                                        mmBannerModelmist.add(responseModel)
                                    } catch (ex: Exception) {

                                    }
                                }
                            }
                            mutableLiveData.value = mmBannerModelmist
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return mutableLiveData
    }
    fun getProductMutableLiveData(index:Int): MutableLiveData<List<ProductModel>> {
        ///ini Retrofit Class
        val userDataService = RetrofitClient.service
        ///call the API that define In Interface
        val call = userDataService.apiRequestProduct(index)
        call.enqueue(object : Callback<ResponseBody> {
            var message: String? = null
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to DeveloperViewModel class
                val gson = GsonBuilder().create()
                if (response.body() != null) {
                    val json = JsonParser().parse(response.body()!!.string()).asJsonObject
                    //Log.e("data",json.toString())
                    if (json != null) {
                        if (json.get("Message").asString == "SUCCESS") {
                            val jsonData = json.getAsJsonObject("Data")
                            val jsonmarketList = jsonData.getAsJsonArray("marketList")
                            if (jsonmarketList != null) {
                                for (i in 0 until jsonmarketList.size()) {
                                    try {
                                        val jsonobj =
                                            JsonParser().parse(
                                                jsonmarketList.get(i).toString()
                                            ).asJsonObject
                                        val responseModel =
                                            gson.fromJson(jsonobj, ProductModel::class.java)
                                        mProductModelmist.add(responseModel)
                                    } catch (ex: Exception) {

                                    }
                                }
                            }
                            mutableProductLiveData.value = mProductModelmist
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return mutableProductLiveData
    }

}
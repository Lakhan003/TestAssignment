package com.app.testassignment.networkcommunication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @get:GET("home?marketCode=UZ")
    val apiRequestBanner: Call<ResponseBody>

    @GET("productlist?productTagId=13&marketCode=UZ")
    fun apiRequestProduct(@Query("page") index:Int): Call<ResponseBody>
}
package com.example.myweatherapp.logic.network

import com.example.myweatherapp.MyWeatherApplication
import com.example.myweatherapp.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    //获取全球大多数城市数据
    //https://api.caiyunapp.com/v2/place?query=北京&token={token}&lang=zh_CN

    //请求 Place 的Service 网络请求 并将请求的JSON格式数据解析成 PlaceResponse 对象
    @GET("/v2/place?token=${MyWeatherApplication.TOKEN}&lang=zh_cn")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}
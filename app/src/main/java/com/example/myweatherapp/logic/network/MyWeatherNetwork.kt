package com.example.myweatherapp.logic.network

import retrofit2.await

object MyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()


}
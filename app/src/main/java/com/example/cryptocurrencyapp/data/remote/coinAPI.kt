package com.example.cryptocurrencyapp.data.remote

import retrofit2.http.GET


interface coinAPI {

    @GET("/v1/coins")
    suspend fun getCoins()
}
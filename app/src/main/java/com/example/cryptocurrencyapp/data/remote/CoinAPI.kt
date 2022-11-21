package com.example.cryptocurrencyapp.data.remote

import com.example.cryptocurrencyapp.data.remote.DTO.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.DTO.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path


interface CoinAPI {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId : String) :CoinDetailsDto
}
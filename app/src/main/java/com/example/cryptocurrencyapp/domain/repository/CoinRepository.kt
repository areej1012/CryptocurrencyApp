package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.remote.DTO.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.DTO.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId:String): CoinDetailsDto
}
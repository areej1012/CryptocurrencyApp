package com.example.cryptocurrencyapp.domain.use_case.get_coins_singular

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.remote.DTO.toCoin
import com.example.cryptocurrencyapp.data.remote.DTO.toCoinDetail
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetail
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsDetailsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId : String): Flow<Resource<CoinDetail>> = flow {
        try {
            // we want execute this use case now and get coins
            // and should display out progress
            emit(Resource.Loading<CoinDetail>())
            val coins = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException){
            emit(Resource.Error("Couldn't reach server, Check your internet connection"))

        }
    }
}
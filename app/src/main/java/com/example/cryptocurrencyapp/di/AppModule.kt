package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.data.remote.CoinAPI
import com.example.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// it's help us to make our dependencies replaceable ex database instance
// pr api instance
@Module
// that mean all the dependencies in the module live as long as app does
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    // ths for make sure there is only oa single instance throughout the whole lifetime
    @Singleton
    fun providePaprikaApi(): CoinAPI{
        return Retrofit.Builder().
                baseUrl(Constants.Base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRep(api : CoinAPI) : CoinRepository{
        return CoinRepositoryImpl(api)
    }
}
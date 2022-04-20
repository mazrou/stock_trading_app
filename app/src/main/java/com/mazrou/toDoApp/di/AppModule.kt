package com.mazrou.toDoApp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSourceImpl
import com.mazrou.toDoApp.business.domain.uitils.Constants
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkService
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkServiceImpl
import com.mazrou.toDoApp.framework.datasource.network.tickers.api.TiingoWebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }

    @Singleton
    @Provides
    fun provideTiingoWebService(retrofitBuilder: Retrofit.Builder): TiingoWebService {
        return retrofitBuilder
            .build()
            .create(TiingoWebService::class.java)
    }

    @Singleton
    @Provides
    fun provideStocksNetworkService(
        tiingoWebService: TiingoWebService
    ): StocksNetworkService {
        return StocksNetworkServiceImpl(tiingoWebService)
    }

    @Singleton
    @Provides
    fun provideStocksNetworkDataSource(
        stocksNetworkService: StocksNetworkService
    ): StocksNetworkDataSource {
        return StocksNetworkDataSourceImpl(stocksNetworkService)
    }
}
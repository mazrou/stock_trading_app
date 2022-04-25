package com.mazrou.toDoApp.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSourceImpl
import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSourceImpl
import com.mazrou.toDoApp.business.domain.uitils.Constants
import com.mazrou.toDoApp.framework.BaseApplication
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkService
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkServiceImpl
import com.mazrou.toDoApp.framework.datasource.network.tickers.api.TiingoWebService
import com.mazrou.toDoApp.framework.datasource.network.trades.TradeNetworkService
import com.mazrou.toDoApp.framework.datasource.network.trades.TradeNetworkServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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


    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun providesTradeNetworkDataSource(
        tradeNetworkService: TradeNetworkService,
    ): TradesNetworkDataSource {
        return TradesNetworkDataSourceImpl(
            tradeNetworkService = tradeNetworkService
        )
    }

    @Singleton
    @Provides
    fun TradeNetworkService(
        @ApplicationContext context: Context,
        fireStore: FirebaseFirestore
    ): TradeNetworkService {
        return TradeNetworkServiceImpl(
            context = context,
            fireStore = fireStore
        )
    }


}
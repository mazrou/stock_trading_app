package com.mazrou.toDoApp.di.tickers

import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.interactors.tickers.SearchStockByTicker
import com.mazrou.toDoApp.business.interactors.tickers.ports.SearchStockByTickerUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StocksModule {


    @Singleton
    @Provides
    fun provideSearchStocksByTicker(
        stocksNetworkDataSource: StocksNetworkDataSource
    ): SearchStockByTickerUserCase {
        return SearchStockByTicker(stocksNetworkDataSource)
    }

}
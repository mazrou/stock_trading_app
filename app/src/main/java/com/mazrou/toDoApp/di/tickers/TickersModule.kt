package com.mazrou.toDoApp.di.tickers

import com.mazrou.toDoApp.business.datasource.network.tickers.TickersNetworkDataSource
import com.mazrou.toDoApp.business.interactors.tickers.GetTickersFromNetwork
import com.mazrou.toDoApp.business.interactors.tickers.ports.GetTickersFromNetworkUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TickersModule {

    @Singleton
    @Provides
    fun provideGetTickers(
        tickersNetworkDataSource: TickersNetworkDataSource
    ): GetTickersFromNetworkUseCase {
        return GetTickersFromNetwork(tickersNetworkDataSource)
    }
}
package com.mazrou.toDoApp.di.trades

import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.interactors.trades.MakeTrade
import com.mazrou.toDoApp.business.interactors.trades.GetCurrentBalance
import com.mazrou.toDoApp.business.interactors.trades.GetTradeHistory
import com.mazrou.toDoApp.business.interactors.trades.ports.MakeTradeUseCase
import com.mazrou.toDoApp.business.interactors.trades.ports.GetCurrentBalanceUseCase
import com.mazrou.toDoApp.business.interactors.trades.ports.GetTradeHistoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TradesModule {

    @Singleton
    @Provides
    fun provideGetCurrentBalance(
        tradeNetworkDataSource: TradesNetworkDataSource
    ): GetCurrentBalanceUseCase {
        return GetCurrentBalance(tradeNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideBuyStock(
        tradeNetworkDataSource: TradesNetworkDataSource
    ): MakeTradeUseCase {
        return MakeTrade(tradeNetworkDataSource)
    }

    @Singleton
    @Provides
    fun provideTradeHistory(
        tradeNetworkDataSource: TradesNetworkDataSource
    ): GetTradeHistoryUseCase {
        return GetTradeHistory(tradeNetworkDataSource)
    }
}
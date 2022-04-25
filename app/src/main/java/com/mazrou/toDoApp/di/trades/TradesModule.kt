package com.mazrou.toDoApp.di.trades

import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.interactors.trades.GetCurrentBalance
import com.mazrou.toDoApp.business.interactors.trades.ports.GetCurrentBalanceUseCase
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

}
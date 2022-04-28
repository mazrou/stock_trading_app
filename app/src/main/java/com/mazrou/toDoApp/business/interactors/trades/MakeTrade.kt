package com.mazrou.toDoApp.business.interactors.trades

import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.trades.ports.MakeTradeUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class MakeTrade(
    private val tradesNetworkDataSource: TradesNetworkDataSource
) : MakeTradeUseCase {
    override fun execute(trade: Trade): Flow<DataState<Boolean>> = flow {
        emit(DataState.loading<Boolean>())

        val tradeResult = tradesNetworkDataSource.makeTrade(trade = trade)
        // emit the result
        emit(DataState.data(null, tradeResult))
    }.catch { e ->
        emit(handleUseCaseException(e))

    }
}

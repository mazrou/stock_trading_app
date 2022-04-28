package com.mazrou.toDoApp.business.interactors.trades

import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.trades.ports.GetTradeHistoryUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetTradeHistory(
    val networkDataSource: TradesNetworkDataSource
) : GetTradeHistoryUseCase {
    override fun execute(): Flow<DataState<List<Trade>>> = flow {
        emit(DataState.loading<List<Trade>>())

        val trades = networkDataSource.getTradeHistory()
        // emit the result
        emit(DataState.data(null, trades))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
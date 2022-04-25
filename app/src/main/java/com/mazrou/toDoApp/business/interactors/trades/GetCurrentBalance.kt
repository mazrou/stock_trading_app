package com.mazrou.toDoApp.business.interactors.trades

import com.mazrou.toDoApp.business.datasource.network.trades.TradesNetworkDataSource
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.trades.ports.GetCurrentBalanceUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import com.mazrou.toDoApp.framework.presentation.ui.stocksList.BalanceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetCurrentBalance(
    private val tradesNetworkDataSource: TradesNetworkDataSource
) : GetCurrentBalanceUseCase{

    // Make sure to execute this on a background thread.
    override fun  execute(): Flow<DataState<Double>> = flow {
      //  emit(DataState.loading<Double>())

        val balance = tradesNetworkDataSource.getBalance()
        // emit the result
        emit(DataState.data(null, balance))
    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
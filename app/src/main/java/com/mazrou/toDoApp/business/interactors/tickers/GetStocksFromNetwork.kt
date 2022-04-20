package com.mazrou.toDoApp.business.interactors.tickers

import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.tickers.ports.GetStocksFromNetworkUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class GetStocksFromNetwork(
    private val stocksNetworkDataSource: StocksNetworkDataSource
) : GetStocksFromNetworkUseCase {

    override fun getStocksFromNetwork(
        tickers: List<String>
    ): Flow<DataState<List<Stock>>> = flow {
        emit(DataState.loading<List<Stock>>())

        // get tickers from network
        val tickersResult = stocksNetworkDataSource.getStocksList(tickers)

        // emit the result
        emit(DataState.data(null, tickersResult))

    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
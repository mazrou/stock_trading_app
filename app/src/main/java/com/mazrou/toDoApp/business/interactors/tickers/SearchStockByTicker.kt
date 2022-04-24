package com.mazrou.toDoApp.business.interactors.tickers

import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.Constants
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.tickers.ports.SearchStockByTickerUserCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchStockByTicker(
    private val stocksNetworkDataSource: StocksNetworkDataSource
) : SearchStockByTickerUserCase {
    override fun execute(
        query: String
    ): Flow<DataState<List<Stock>>> = flow {
        emit(DataState.loading<List<Stock>>())

        // get stocks from network
        val stocksResult = stocksNetworkDataSource.getStocksList(
            if (query.isEmpty()) Constants.TICKERS_LIST else listOf(query)
        )
        // emit the result
        emit(DataState.data(null, stocksResult))

    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
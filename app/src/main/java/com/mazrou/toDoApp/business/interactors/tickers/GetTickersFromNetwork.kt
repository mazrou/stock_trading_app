package com.mazrou.toDoApp.business.interactors.tickers

import com.mazrou.toDoApp.business.datasource.network.tickers.TickersNetworkDataSource
import com.mazrou.toDoApp.business.domain.models.Ticker
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.tickers.ports.GetTickersFromNetworkUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.catch

class GetTickersFromNetwork(
    private val tickersNetworkDataSource: TickersNetworkDataSource
) : GetTickersFromNetworkUseCase {

    override fun getTickersFromNetwork(
        tickers: List<String>
    ): Flow<DataState<List<Ticker>>> = flow {
        emit(DataState.loading<List<Ticker>>())

        // get tickers from network
        val tickersResult = tickersNetworkDataSource.getTickersList(tickers)

        // emit the result
        emit(DataState.data(null, tickersResult))

    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
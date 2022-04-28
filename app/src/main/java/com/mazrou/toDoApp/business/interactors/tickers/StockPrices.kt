package com.mazrou.toDoApp.business.interactors.tickers

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.datasource.network.tickers.StocksNetworkDataSource
import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.business.interactors.tickers.ports.StockPricesUseCase
import com.mazrou.toDoApp.framework.datasource.network.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.util.*

class StockPrices(
    private val stocksNetworkDataSource: StocksNetworkDataSource
) : StockPricesUseCase {

    override fun execute(ticker: String, date: LocalDate
    ): Flow<DataState<List<CandleEntry>>> = flow {
        emit(DataState.loading<List<CandleEntry>>())

        // get stocks from network
        val result = stocksNetworkDataSource.getStockDetail(
            ticker = ticker, date = date
        )
        // emit the result
        emit(DataState.data(null, result))

    }.catch { e ->
        emit(handleUseCaseException(e))
    }
}
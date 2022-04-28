package com.mazrou.toDoApp.business.datasource.network.tickers

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkService
import java.time.LocalDate
import java.util.*

class StocksNetworkDataSourceImpl(
    private val stocksNetworkService: StocksNetworkService
) : StocksNetworkDataSource {

    override suspend fun getStocksList(tickers: List<String>): List<Stock> {
        return stocksNetworkService.getStocksList(tickers = tickers)
    }

    override suspend fun getStockDetail(ticker: String, date: LocalDate): List<CandleEntry> {
        return stocksNetworkService.getStockDetail(ticker = ticker, startDate = date)
    }
}
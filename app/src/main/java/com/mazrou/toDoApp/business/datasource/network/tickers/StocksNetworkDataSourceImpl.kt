package com.mazrou.toDoApp.business.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.framework.datasource.network.tickers.StocksNetworkService

class StocksNetworkDataSourceImpl(
    private val stocksNetworkService: StocksNetworkService
) : StocksNetworkDataSource {

    override suspend fun getStocksList(tickers: List<String>): List<Stock> {
        return stocksNetworkService.getStocksList(tickers = tickers)
    }
}
package com.mazrou.toDoApp.business.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Ticker
import com.mazrou.toDoApp.framework.datasource.network.tickers.TickersNetworkService

class TickersNetworkDataSourceImpl(
    private val tickersNetworkService: TickersNetworkService
) : TickersNetworkDataSource {

    override suspend fun getTickersList(tickers: List<String>): List<Ticker> {
        return tickersNetworkService.getTickersList(tickers = tickers)
    }
}
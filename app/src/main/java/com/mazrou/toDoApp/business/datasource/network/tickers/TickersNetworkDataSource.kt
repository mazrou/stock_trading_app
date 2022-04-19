package com.mazrou.toDoApp.business.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Ticker

interface TickersNetworkDataSource {

    suspend fun getTickersList(
        tickers: List<String>,
    ): List<Ticker>

}
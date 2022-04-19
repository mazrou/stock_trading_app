package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Ticker

interface TickersNetworkService {

    suspend fun getTickersList(
        tickers: List<String>,
    ): List<Ticker>
}
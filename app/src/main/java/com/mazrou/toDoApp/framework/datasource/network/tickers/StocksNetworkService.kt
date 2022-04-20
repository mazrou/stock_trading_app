package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Stock

interface StocksNetworkService {

    suspend fun getStocksList(
        tickers: List<String>,
    ): List<Stock>
}
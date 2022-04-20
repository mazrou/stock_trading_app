package com.mazrou.toDoApp.business.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Stock

interface StocksNetworkDataSource {

    suspend fun getStocksList(
        tickers: List<String>,
    ): List<Stock>

}
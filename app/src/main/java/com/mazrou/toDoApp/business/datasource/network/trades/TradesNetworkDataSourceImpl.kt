package com.mazrou.toDoApp.business.datasource.network.trades

import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.framework.datasource.network.trades.TradeNetworkService

class TradesNetworkDataSourceImpl(
    private val tradeNetworkService: TradeNetworkService
) : TradesNetworkDataSource {
    override suspend fun getBalance(): Double {
        return tradeNetworkService.getBalance()
    }

    override suspend fun buyStock(trade : Trade): Boolean {
        return tradeNetworkService.buyStock(trade)
    }
}
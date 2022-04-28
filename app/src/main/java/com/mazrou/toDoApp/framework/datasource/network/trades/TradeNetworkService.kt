package com.mazrou.toDoApp.framework.datasource.network.trades

import com.mazrou.toDoApp.business.domain.models.Trade

interface TradeNetworkService {


    suspend fun getBalance(): Double
    suspend fun setBalance(balance : Double)
    suspend fun makeTrade(trade : Trade) : Boolean
    suspend fun getTradeHistory() : List<Trade>

}
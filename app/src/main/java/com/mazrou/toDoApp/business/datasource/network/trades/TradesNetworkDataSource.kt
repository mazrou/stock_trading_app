package com.mazrou.toDoApp.business.datasource.network.trades

import com.mazrou.toDoApp.business.domain.models.Trade

interface TradesNetworkDataSource {

    suspend fun getBalance() : Double

    suspend fun buyStock(trade : Trade) : Boolean

}
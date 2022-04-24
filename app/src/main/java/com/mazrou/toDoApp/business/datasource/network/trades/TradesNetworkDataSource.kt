package com.mazrou.toDoApp.business.datasource.network.trades

interface TradesNetworkDataSource {

    suspend fun getBalance() : Double
}
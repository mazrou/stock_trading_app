package com.mazrou.toDoApp.framework.datasource.network.trades

interface TradeNetworkService {


    suspend fun getBalance(): Double
    suspend fun setBalance(balance : Double)

}
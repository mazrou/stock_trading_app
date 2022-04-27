package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.models.Stock
import java.time.LocalDate
import java.util.*

interface StocksNetworkService {

    suspend fun getStocksList(
        tickers: List<String>,
    ): List<Stock>

    suspend fun getStockDetail(
        ticker : String,
        startDate : LocalDate
    ) : List<CandleEntry>
}
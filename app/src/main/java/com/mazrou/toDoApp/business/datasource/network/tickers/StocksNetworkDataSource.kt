package com.mazrou.toDoApp.business.datasource.network.tickers

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.models.Stock
import java.time.LocalDate
import java.util.*

interface StocksNetworkDataSource {

    suspend fun getStocksList(
        tickers: List<String>,
    ): List<Stock>

    suspend fun getStockDetail(
        ticker : String,
        data : LocalDate
    ) : List<CandleEntry>

}
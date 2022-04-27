package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.DateUtils
import com.mazrou.toDoApp.framework.datasource.network.tickers.api.TiingoWebService
import java.time.LocalDate
import java.util.*

class StocksNetworkServiceImpl(
    private val webservice: TiingoWebService
) : StocksNetworkService {

    override suspend fun getStocksList(tickers: List<String>): List<Stock> {
        return webservice.getStocksList(tickers = tickers.toStringIterated()).map {
            it.toTicker()
        }
    }

    override suspend fun getStockDetail(ticker: String, startDate: LocalDate): List<CandleEntry> {
        return webservice.getStockDetail(ticker, DateUtils.convertLongToStringDate(startDate.toEpochDay()))
            .mapIndexed { index, stockDetailDto ->
                stockDetailDto.toCandleEntry((index + 1).toFloat())
            }
    }

    private fun <E> List<E>.toStringIterated(): String {
        val result = StringBuilder()
        this.forEachIndexed { i, e ->
            result.append(e.toString().lowercase())
            if (i != this.size - 1) {
                result.append(",")
            }
        }
        return result.toString()
    }
}

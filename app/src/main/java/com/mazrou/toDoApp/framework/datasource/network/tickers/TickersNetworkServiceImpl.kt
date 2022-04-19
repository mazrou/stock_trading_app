package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Ticker
import com.mazrou.toDoApp.framework.datasource.network.tickers.api.TiingoWebService

class TickersNetworkServiceImpl(
    private val webservice: TiingoWebService
) : TickersNetworkService {

    override suspend fun getTickersList(tickers: List<String>): List<Ticker> {
        return webservice.getTickersList(tickers = tickers.toStringIterated()).map {
            it.toTicker()
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

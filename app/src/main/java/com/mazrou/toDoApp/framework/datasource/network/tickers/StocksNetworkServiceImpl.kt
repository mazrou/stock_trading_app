package com.mazrou.toDoApp.framework.datasource.network.tickers

import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.framework.datasource.network.tickers.api.TiingoWebService

class StocksNetworkServiceImpl(
    private val webservice: TiingoWebService
) : StocksNetworkService {

    override suspend fun getStocksList(tickers: List<String>): List<Stock> {
        return webservice.getStocksList(tickers = tickers.toStringIterated()).map {
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

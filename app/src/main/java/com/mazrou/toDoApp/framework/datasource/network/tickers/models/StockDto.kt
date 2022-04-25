package com.mazrou.toDoApp.framework.datasource.network.tickers.models

import com.mazrou.toDoApp.business.domain.models.Stock
import java.util.*

data class StockDto(
    val ticker: String,
    val lastSaleTimestamp: Date?,
    val timestamp: Date?,
    val quoteTimestamp: Date?,
    val last: Double,
    val prevClose: Double,
    val lastSize: Int?,
    val tiingoLast: Double?,
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val mid: Double?,
    val volume: Long?,
    val bidSize: Double?,
    val bidPrice: Double?,
    val askSize: Double?,
    val askPrice: Double?
) {
    fun toTicker(): Stock {
        return Stock(
            ticker,
            timestamp,
            quoteTimestamp,
            lastSaleTimestamp,
            last,
            lastSize,
            tiingoLast,
            prevClose,
            open,
            high,
            low,
            mid,
            volume,
            bidSize,
            bidPrice,
            askSize,
            askPrice
        )
    }
}

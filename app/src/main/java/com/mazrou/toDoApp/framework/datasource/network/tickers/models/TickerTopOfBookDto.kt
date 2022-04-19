package com.mazrou.toDoApp.framework.datasource.network.tickers.models

import com.mazrou.toDoApp.business.domain.models.Ticker
import java.util.*

data class TickerTopOfBookDto(
    val ticker: String,
    val timestamp: Date,
    val quoteTimestamp: Date,
    val lastSaleTimestamp: Date,
    val last: Double,
    val lastSize: Int?,
    val tiingoLast: Double,
    val prevClose: Double,
    val open: Double,
    val high: Double,
    val low: Double,
    val mid: Double?,
    val volume: Long?,
    val bidSize: Double?,
    val bidPrice: Double?,
    val askSize: Double?,
    val askPrice: Double?
) {
    fun toTicker(): Ticker {
        return Ticker(
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

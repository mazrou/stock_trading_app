package com.mazrou.toDoApp.business.domain.models

import java.util.*

data class Stock(
    val ticker: String,
    val timestamp: Date?,
    val quoteTimestamp: Date?,
    val lastSaleTimestamp: Date?,
    val last: Double,
    val lastSize: Int?,
    val tiingoLast: Double?,
    val prevClose: Double,
    val open: Double?,
    val high: Double?,
    val low: Double?,
    val mid: Double?,
    val volume: Long?,
    val bidSize: Double?,
    val bidPrice: Double?,
    val askSize: Double?,
    val askPrice: Double?
)
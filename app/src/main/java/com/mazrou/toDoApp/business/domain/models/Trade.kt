package com.mazrou.toDoApp.business.domain.models

import com.mazrou.toDoApp.framework.datasource.network.trades.models.TradeDto
import java.time.LocalDate
import java.time.LocalDateTime

data class Trade(
    val price: Double,
    val ticker: String,
    val date: LocalDateTime,
    val quantity: Int,
    val type: TradingType
) {
    fun toTradeDto(): TradeDto {
        return TradeDto(
            price = price,
            ticker = ticker,
            quantity = quantity,
            type = type.name
        )
    }
}
    enum class TradingType {
        BUY, SELL
    }
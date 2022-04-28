package com.mazrou.toDoApp.business.domain.models

import com.mazrou.toDoApp.framework.datasource.network.trades.models.TradeDto
import java.time.LocalDate

data class Trade(
    val price: Double,
    val ticker: String,
    val date: LocalDate,
    val quantity: Int,
    val type: TradingType
) {
    fun toTradeDto(): TradeDto {
        return TradeDto(
            price = price,
            ticker = ticker,
            quantity = quantity,
            type = type
        )
    }
}
    enum class TradingType {
        BUY, SELL
    }
package com.mazrou.toDoApp.framework.datasource.network.trades.models

import com.mazrou.toDoApp.business.domain.models.TradingType

data class TradeDto(

    val price: Double,
    val ticker: String,
    val quantity: Int,
    val type: TradingType
)
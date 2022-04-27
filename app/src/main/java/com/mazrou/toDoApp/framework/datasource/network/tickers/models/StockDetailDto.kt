package com.mazrou.toDoApp.framework.datasource.network.tickers.models

import com.github.mikephil.charting.data.CandleEntry


data class StockDetailDto(
    val close: Float,
    val date: String,
    val high: Float,
    val low: Float,
    val open: Float,
    val volume: Int
){
    fun toCandleEntry(position : Float) : CandleEntry{
        return CandleEntry(
            position,
            high,
            low,
            open,
            close
        )
    }
}
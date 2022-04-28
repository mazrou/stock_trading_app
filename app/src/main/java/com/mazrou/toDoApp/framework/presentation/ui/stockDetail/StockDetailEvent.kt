package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import com.mazrou.toDoApp.framework.presentation.Event
import java.time.LocalDate


sealed class StockDetailEvent : Event {
    data class ImportStockPrice(
        val ticker: String,
        val date: LocalDate = LocalDate.now().minusDays(1)
    ) : StockDetailEvent()

    data class BuyStock(
        val ticker : String ,
        val unitPrice : Double ,
        val quantity : Int
    ) : StockDetailEvent()

    data class SellStock(
        val ticker : String ,
        val unitPrice : Double ,
        val quantity : Int
    ) : StockDetailEvent()
}
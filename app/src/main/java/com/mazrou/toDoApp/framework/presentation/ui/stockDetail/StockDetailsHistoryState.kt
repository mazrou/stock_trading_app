package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.uitils.Queue
import com.mazrou.toDoApp.business.domain.uitils.StateMessage


sealed class StockDetailState{

    data class StockDetailsHistoryState(
        var data: MutableList<CandleEntry> = mutableListOf(),
        var isLoading: Boolean = false,
        val queue: Queue<StateMessage> = Queue(mutableListOf())
    ):StockDetailState()

    data class BuyingStockState(
        var isLoading: Boolean = false,
        var isBuyingSuccess: Boolean = false,
        val queue: Queue<StateMessage> = Queue(mutableListOf())
    ):StockDetailState()
}

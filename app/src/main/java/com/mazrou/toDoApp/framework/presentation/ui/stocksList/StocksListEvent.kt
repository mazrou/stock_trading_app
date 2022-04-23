package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import com.mazrou.toDoApp.framework.presentation.Event

sealed class StocksListEvent : Event {

    data class GetStocksFromNetwork(
        val tickers: List<String>
    ) : Event
}
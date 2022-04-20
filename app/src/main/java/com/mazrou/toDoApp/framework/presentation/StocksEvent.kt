package com.mazrou.toDoApp.framework.presentation

sealed class StocksEvent : Event {

    data class GetStocksFromNetwork(
        val tickers: List<String>
    ) : Event
}
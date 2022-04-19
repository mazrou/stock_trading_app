package com.mazrou.toDoApp.framework.presentation

sealed class TickersEvent : Event {

    data class GetTickersFromNetwork(
        val tickers: List<String>
    ) : Event
}
package com.mazrou.toDoApp.framework.presentation

import com.mazrou.toDoApp.business.domain.models.Ticker
import com.mazrou.toDoApp.business.domain.uitils.Queue
import com.mazrou.toDoApp.business.domain.uitils.StateMessage

data class TickersState(
    val isLoading: Boolean = false,
    val tickers: List<Ticker>? = null,
    val queue: Queue<StateMessage> = Queue(mutableListOf())
)
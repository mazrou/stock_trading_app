package com.mazrou.toDoApp.framework.presentation

import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.Queue
import com.mazrou.toDoApp.business.domain.uitils.StateMessage

data class StocksState(
    val isLoading: Boolean = false,
    val stocks: List<Stock> = listOf(),
    val queue: Queue<StateMessage> = Queue(mutableListOf())
)
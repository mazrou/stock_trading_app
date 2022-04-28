package com.mazrou.toDoApp.framework.presentation.ui.tradeHistory

import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.uitils.Queue
import com.mazrou.toDoApp.business.domain.uitils.StateMessage

data class TradeHistoryState(
    val isLoading: Boolean = false,
    val trades: List<Trade> = listOf(),
    var query: String = "",
    val queue: Queue<StateMessage> = Queue(mutableListOf())
)


package com.mazrou.toDoApp.framework.presentation.ui.tradeHistory

import com.mazrou.toDoApp.framework.presentation.Event

sealed class TradeHistoryEvent : Event {
    object GetAllTrades : TradeHistoryEvent()
}
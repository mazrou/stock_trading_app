package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import com.mazrou.toDoApp.framework.presentation.Event

sealed class StocksListEvent : Event {

    object CurrentBalance : Event

    object NewSearch : Event
}
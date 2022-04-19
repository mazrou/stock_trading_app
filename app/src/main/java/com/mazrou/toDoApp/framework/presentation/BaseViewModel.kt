package com.mazrou.toDoApp.framework.presentation

import androidx.lifecycle.ViewModel
import com.mazrou.toDoApp.business.domain.uitils.StateMessage

abstract class BaseViewModel: ViewModel() {
    val TAG: String = "AppDebug"

    abstract fun onTriggerEvent(event: Event)
    abstract fun removeHeadFromQueue()
    abstract fun appendToMessageQueue(stateMessage: StateMessage)
}
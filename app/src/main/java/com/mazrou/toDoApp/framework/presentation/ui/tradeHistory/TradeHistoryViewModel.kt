package com.mazrou.toDoApp.framework.presentation.ui.tradeHistory

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.mazrou.toDoApp.business.domain.uitils.StateMessage
import com.mazrou.toDoApp.business.domain.uitils.UIComponentType
import com.mazrou.toDoApp.business.domain.uitils.doesMessageAlreadyExistInQueue
import com.mazrou.toDoApp.business.interactors.trades.ports.GetTradeHistoryUseCase
import com.mazrou.toDoApp.framework.presentation.BaseViewModel
import com.mazrou.toDoApp.framework.presentation.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TradeHistoryViewModel
@Inject
constructor(
    private val getTradeHistoryUseCase: GetTradeHistoryUseCase
) : BaseViewModel() {
    val state = mutableStateOf(TradeHistoryState())
    val onLoad = mutableStateOf(false)

    override fun onTriggerEvent(event: Event) {
        when (event){
            is TradeHistoryEvent.GetAllTrades ->{
                getTradeHistory()
            }
        }

    }

    private fun getTradeHistory() {
        state.value.let { state ->
            getTradeHistoryUseCase.execute(
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)
                dataState.data?.let { trades ->
                    this.state.value = state.copy(trades = trades)
                }
                dataState.stateMessage?.let { stateMessage ->
                    appendToMessageQueue(stateMessage)
                }
            }.launchIn(viewModelScope)
        }
    }

    override fun removeHeadFromQueue() {
        state.value.let { state ->
            try {
                val queue = state.queue
                queue.remove() // can throw exception if empty
                this.state.value = state.copy(queue = queue)
            } catch (e: Exception) {
                Log.d(TAG, "removeHeadFromQueue: Nothing to remove from DialogQueue")
            }
        }
    }

    override fun appendToMessageQueue(stateMessage: StateMessage) {
        state.value.let { state ->
            val queue = state.queue
            if (!stateMessage.doesMessageAlreadyExistInQueue(queue = queue)) {
                if (stateMessage.response.uiComponentType !is UIComponentType.None) {
                    queue.add(stateMessage)
                    this.state.value = state.copy(queue = queue)
                }
            }
        }
    }
}
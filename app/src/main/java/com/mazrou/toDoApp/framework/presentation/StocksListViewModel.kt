package com.mazrou.toDoApp.framework.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.mazrou.toDoApp.business.domain.uitils.StateMessage
import com.mazrou.toDoApp.business.domain.uitils.UIComponentType
import com.mazrou.toDoApp.business.domain.uitils.doesMessageAlreadyExistInQueue
import com.mazrou.toDoApp.business.interactors.tickers.ports.GetStocksFromNetworkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StocksListViewModel
@Inject
constructor(
    private val getTickersFromNetworkUseCase: GetStocksFromNetworkUseCase
) : BaseViewModel() {

    val state: MutableState<StocksState> = mutableStateOf(StocksState())

    init {
        onTriggerEvent(
            StocksEvent.GetStocksFromNetwork(
                listOf("FBC", "AAPL")
            )
        )
    }

    override fun onTriggerEvent(event: Event) {
        when (event) {
            is StocksEvent.GetStocksFromNetwork -> {
                getTickersFromNetwork(event.tickers)
            }
        }
    }

    private fun getTickersFromNetwork(tickers: List<String>) {
        state.value.let { state ->
            getTickersFromNetworkUseCase.getStocksFromNetwork(
                tickers = tickers
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)
                dataState.data?.let { tickers ->
                    this.state.value = state.copy(stocks = tickers)
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
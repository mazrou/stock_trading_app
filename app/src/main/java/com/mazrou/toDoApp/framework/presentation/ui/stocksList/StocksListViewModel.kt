package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.mazrou.toDoApp.business.domain.uitils.StateMessage
import com.mazrou.toDoApp.business.domain.uitils.UIComponentType
import com.mazrou.toDoApp.business.domain.uitils.doesMessageAlreadyExistInQueue
import com.mazrou.toDoApp.business.interactors.tickers.ports.GetStocksFromNetworkUseCase
import com.mazrou.toDoApp.framework.presentation.BaseViewModel
import com.mazrou.toDoApp.framework.presentation.Event
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
            StocksListEvent.GetStocksFromNetwork(
                listOf("FBC", "AAPL", "GLG", "MCF", "SPY")
            )
        )
    }

    override fun onTriggerEvent(event: Event) {
        when (event) {
            is StocksListEvent.GetStocksFromNetwork -> {
                getTickersFromNetwork(event.tickers)
            }
        }
    }

    private fun getTickersFromNetwork(tickers: List<String>) {
        state.value.let { state ->
            getTickersFromNetworkUseCase.execute(
                tickers = tickers
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)
                dataState.data?.let { stocks ->
                    this.state.value = state.copy(stocks = stocks)
                }
                dataState.stateMessage?.let { stateMessage ->
                    appendToMessageQueue(stateMessage)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }
    private fun setQuery(query: String){
        this.state.value = state.value.copy(query = query)
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
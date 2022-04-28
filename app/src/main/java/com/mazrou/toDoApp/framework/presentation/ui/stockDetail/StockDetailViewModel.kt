package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.models.TradingType
import com.mazrou.toDoApp.business.domain.uitils.StateMessage
import com.mazrou.toDoApp.business.domain.uitils.UIComponentType
import com.mazrou.toDoApp.business.domain.uitils.doesMessageAlreadyExistInQueue
import com.mazrou.toDoApp.business.interactors.tickers.ports.StockPricesUseCase
import com.mazrou.toDoApp.business.interactors.trades.ports.BuyStockUseCase
import com.mazrou.toDoApp.framework.presentation.BaseViewModel
import com.mazrou.toDoApp.framework.presentation.Event
import com.mazrou.toDoApp.framework.presentation.ui.stockDetail.StockDetailState.BuyingStockState
import com.mazrou.toDoApp.framework.presentation.ui.stockDetail.StockDetailState.StockDetailsHistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class StockDetailViewModel
@Inject
constructor(
    private val stockPricesUseCase: StockPricesUseCase,
    private val buyStockUseCase: BuyStockUseCase
) : BaseViewModel() {

    val state: MutableState<StockDetailsHistoryState> = mutableStateOf(StockDetailsHistoryState())
    val buyState = mutableStateOf(BuyingStockState())

    val isBuyingEventTrigger = mutableStateOf(false)
    val onLoadChartData = mutableStateOf(false)

    override fun onTriggerEvent(event: Event) {
        when (event) {
            is StockDetailEvent.ImportStockPrice -> {
                importStockPrice(event.ticker, event.date)
            }
            is StockDetailEvent.BuyStock -> {
                buyStock(
                    trade = Trade(
                        ticker = event.ticker,
                        quantity = event.quantity,
                        type = TradingType.BUY,
                        price = event.unitPrice,
                        date = LocalDateTime.now()
                    )
                )
            }
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

    private fun buyStock(trade: Trade) {
        // to buy it just one time
        buyState.value.let { state ->
            buyStockUseCase.execute(
                trade = trade
            )
                .onEach { dataState ->
                    this.buyState.value = state.copy(isLoading = dataState.isLoading)
                    dataState.data?.let { data ->
                        this.buyState.value = state.copy(isBuyingSuccess = data)
                    }
                    dataState.stateMessage?.let { stateMessage ->
                        appendToMessageQueue(stateMessage)
                    }
                }.launchIn(viewModelScope)
        }
    }

    private fun importStockPrice(ticker: String, date: LocalDate) {
        state.value.let { state ->
            stockPricesUseCase.execute(
                ticker = ticker,
                date = date
            ).onEach { dataState ->
                this.state.value = state.copy(isLoading = dataState.isLoading)
                dataState.data?.let { data ->
                    this.state.value =
                        state.copy(
                            data = if (data.size > 6) data.takeLast(6)
                                .toMutableList() else data.toMutableList()
                        )
                }
                dataState.stateMessage?.let { stateMessage ->
                    appendToMessageQueue(stateMessage)
                }
            }.launchIn(viewModelScope)

        }
    }
}
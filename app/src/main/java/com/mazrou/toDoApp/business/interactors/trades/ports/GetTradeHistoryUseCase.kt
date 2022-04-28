package com.mazrou.toDoApp.business.interactors.trades.ports

import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow

interface GetTradeHistoryUseCase {

    fun execute(): Flow<DataState<List<Trade>>>
}
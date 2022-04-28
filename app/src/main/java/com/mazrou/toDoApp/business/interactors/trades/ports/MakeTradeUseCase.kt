package com.mazrou.toDoApp.business.interactors.trades.ports

import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow

interface MakeTradeUseCase {

    fun execute(
        trade : Trade
    ): Flow<DataState<Boolean>>
}
package com.mazrou.toDoApp.business.interactors.trades.ports

import com.mazrou.toDoApp.business.domain.uitils.DataState
import com.mazrou.toDoApp.framework.presentation.ui.stocksList.BalanceState
import kotlinx.coroutines.flow.Flow

interface GetCurrentBalanceUseCase {

    fun execute(): Flow<DataState<Double>>
}
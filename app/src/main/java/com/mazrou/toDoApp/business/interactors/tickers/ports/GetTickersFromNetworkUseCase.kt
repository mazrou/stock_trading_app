package com.mazrou.toDoApp.business.interactors.tickers.ports

import com.mazrou.toDoApp.business.domain.models.Ticker
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow

interface GetTickersFromNetworkUseCase {

    fun getTickersFromNetwork(
        tickers: List<String>,
    ): Flow<DataState<List<Ticker>>>
}
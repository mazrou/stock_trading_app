package com.mazrou.toDoApp.business.interactors.tickers.ports

import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow

interface GetStocksFromNetworkUseCase {

    fun getStocksFromNetwork(
        tickers: List<String>,
    ): Flow<DataState<List<Stock>>>
}
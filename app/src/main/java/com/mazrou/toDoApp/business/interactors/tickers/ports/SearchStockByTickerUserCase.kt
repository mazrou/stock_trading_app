package com.mazrou.toDoApp.business.interactors.tickers.ports

import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow

interface SearchStockByTickerUserCase {
    fun execute(
        query: String
    ): Flow<DataState<List<Stock>>>
}
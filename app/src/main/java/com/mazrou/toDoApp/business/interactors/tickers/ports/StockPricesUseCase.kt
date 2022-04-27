package com.mazrou.toDoApp.business.interactors.tickers.ports

import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.business.domain.uitils.DataState
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*

interface StockPricesUseCase {

    fun execute(
        ticker: String,
        date: LocalDate
    ): Flow<DataState<List<CandleEntry>>>
}
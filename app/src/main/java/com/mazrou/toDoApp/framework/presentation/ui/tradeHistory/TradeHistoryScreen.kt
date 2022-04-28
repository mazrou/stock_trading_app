package com.mazrou.toDoApp.framework.presentation.ui.tradeHistory

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.framework.presentation.components.BalanceSurface
import com.mazrou.toDoApp.framework.presentation.components.SearchAppBar
import com.mazrou.toDoApp.framework.presentation.components.StocksList
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme
import com.mazrou.toDoApp.framework.presentation.ui.stocksList.StocksListEvent

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun TradeHistoryScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: TradeHistoryViewModel
) {

    val onLoad = viewModel.onLoad.value

    if (!onLoad){
        viewModel.onTriggerEvent(TradeHistoryEvent.GetAllTrades)
    }
    val scaffoldState = rememberScaffoldState()
    AppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        scaffoldState = scaffoldState
    ) {
        Scaffold {
           /* StocksList(
                loading = stocksLoading,
                stocks = stocks,
                onNavigateToStockDetailScreen = onNavigateToStockDetailScreen
            ) */
        }
    }
}
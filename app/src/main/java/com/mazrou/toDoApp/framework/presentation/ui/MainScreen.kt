package com.mazrou.toDoApp.framework.presentation.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mazrou.toDoApp.framework.presentation.StocksListViewModel
import com.mazrou.toDoApp.framework.presentation.components.StocksList
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: StocksListViewModel,
    onNavigateToStockDetailScreen: (String) -> Unit,
) {
    val loading = viewModel.state.value.isLoading
    val stocks = viewModel.state.value.stocks
    val scaffoldState = rememberScaffoldState()
    AppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        scaffoldState = scaffoldState
    ) {
        Scaffold {
            StocksList(
                loading = loading,
                stocks = stocks,
                onNavigateToStockDetailScreen = onNavigateToStockDetailScreen
            )
        }
    }
}
package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mazrou.toDoApp.framework.presentation.components.BalanceSurface
import com.mazrou.toDoApp.framework.presentation.components.SearchAppBar
import com.mazrou.toDoApp.framework.presentation.components.StocksList
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme

@ExperimentalComposeUiApi
@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun MainScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: StocksListViewModel,
    onNavigateToStockDetailScreen: (String) -> Unit,
) {
    val stocksLoading = viewModel.state.value.isLoading
    val stocks = viewModel.state.value.stocks
    val query = viewModel.state.value.query
    val balance = viewModel.balanceState.value.balance
    val scaffoldState = rememberScaffoldState()
    AppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        scaffoldState = scaffoldState
    ) {
        Scaffold(
            topBar = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    BalanceSurface(
                        balance = balance
                    )

                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = {
                            viewModel.onTriggerEvent(StocksListEvent.NewSearch)
                        }
                    )
                }

            }

        ) {
            StocksList(
                loading = stocksLoading,
                stocks = stocks,
                onNavigateToStockDetailScreen = onNavigateToStockDetailScreen
            )
        }
    }
}
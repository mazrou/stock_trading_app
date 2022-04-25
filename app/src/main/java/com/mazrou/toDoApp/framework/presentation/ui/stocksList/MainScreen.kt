package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
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

            },
            bottomBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    FloatingActionButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = "Float",
                            modifier = Modifier
                                .background(MaterialTheme.colors.primary)

                                .padding(16.dp)
                        )
                    }
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
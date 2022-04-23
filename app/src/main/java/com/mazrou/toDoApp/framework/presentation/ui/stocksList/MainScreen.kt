package com.mazrou.toDoApp.framework.presentation.ui.stocksList

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
    val loading = viewModel.state.value.isLoading
    val stocks = viewModel.state.value.stocks
    val query = viewModel.state.value.query
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
                        balance = 1000.0
                    )

                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = {
                            //viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                        }
                    )
                }

            }

        ) {
            StocksList(
                loading = loading,
                stocks = stocks,
                onNavigateToStockDetailScreen = onNavigateToStockDetailScreen
            )
        }
    }
}
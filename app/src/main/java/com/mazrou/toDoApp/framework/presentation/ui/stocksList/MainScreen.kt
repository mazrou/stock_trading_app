package com.mazrou.toDoApp.framework.presentation.ui.stocksList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
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
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "My balance",
                            modifier = Modifier
                                .padding(16.dp, 16.dp, 0.dp, 0.dp),
                            style = MaterialTheme.typography.h6,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Text(
                            text = "$1000",
                            modifier = Modifier
                                .padding(0.dp, 16.dp, 16.dp, 0.dp),
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                    Button(
                        onClick = {}, modifier = Modifier
                            .align(Alignment.End)
                            .padding(10.dp)
                            .background(MaterialTheme.colors.background)

                    ) {
                        Icon(Icons.Filled.Done, contentDescription = "See my trades")
                        Text(text = "See my trades")
                    }
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
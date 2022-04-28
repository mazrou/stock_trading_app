package com.mazrou.toDoApp.framework.presentation.ui.tradeHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.framework.presentation.components.TradeList
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme

@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun TradeHistoryScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: TradeHistoryViewModel
) {

    val onLoad = viewModel.onLoad.value
    val state = viewModel.state.value

    if (!onLoad) {
        viewModel.onTriggerEvent(TradeHistoryEvent.GetAllTrades)
    }
    val scaffoldState = rememberScaffoldState()
    AppTheme(
        darkTheme = isDarkTheme,
        isNetworkAvailable = isNetworkAvailable,
        scaffoldState = scaffoldState
    ) {
        Scaffold(
            topBar = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.surface),
                    shape = MaterialTheme.shapes.small,
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Trades History",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                    }

                }
            }
        ) {
            TradeList(
                loading = state.isLoading,
                trades = state.trades,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}
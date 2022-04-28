package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.framework.presentation.components.CandleStickChartView
import com.mazrou.toDoApp.framework.presentation.components.NothingHere
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme


@ExperimentalUnitApi
@ExperimentalMaterialApi
@Composable
fun StockDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    ticker: String?,
    last: Double?,
    previousClose: Double?,
    viewModel: StockDetailViewModel
) {

    if (ticker == null || last == null || previousClose == null) {
        // show an error
    } else {
        val data = viewModel.state.value.data
        val isLoading = viewModel.state.value.isLoading
        val scaffoldState = rememberScaffoldState()
        val onLoadChartData = viewModel.onLoadChartData.value

        if (!onLoadChartData) {
            viewModel.onLoadChartData.value = true
            viewModel.onTriggerEvent(
                StockDetailEvent
                    .ImportStockPrice(ticker = ticker)
            )
        }

        AppTheme(
            darkTheme = isDarkTheme,
            isNetworkAvailable = isNetworkAvailable,
            scaffoldState = scaffoldState
        ) {
            Scaffold(
                modifier = Modifier.fillMaxWidth(),
                topBar = {

                },
                bottomBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(50.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                .align(Top),
                            onClick = {
                                if (!viewModel.isBuyingEventTrigger.value) {
                                    viewModel.onTriggerEvent(
                                        StockDetailEvent.BuyStock(
                                            ticker = ticker,
                                            unitPrice = last,
                                            quantity = 1,

                                            )
                                    )
                                }
                            }) {
                            Box(modifier = Modifier.padding(30.dp, 5.dp)) {
                                Text(text = "Buy", style = MaterialTheme.typography.h6)
                            }
                        }
                    }
                }
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(19.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.onPrimary,
                            text = ticker
                        )
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {

                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.h5,
                                color = MaterialTheme.colors.onPrimary,
                                text = "$" + String.format("%.2f", (last))
                            )
                            Text(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.subtitle1,
                                text = "$" + String.format("%.2f", (last - previousClose)),
                                color = if (previousClose > last) {
                                    Color.Red
                                } else {
                                    Color.Green
                                }
                            )
                        }
                    }
                    if (isLoading && data.isEmpty()) {
                        // show progress
                    } else if (data.isEmpty()) {
                        NothingHere()
                    } else if (data.isNotEmpty()) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background)
                        ) {
                            CandleStickChartView(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxHeight(.7f)
                                    .fillMaxWidth(.9f)
                                    .align(Alignment.CenterHorizontally),
                                data = data,
                                ticker = ticker
                            )
                        }
                    }
                }
            }
        }
    }
}
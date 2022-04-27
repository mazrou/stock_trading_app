package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
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


    // Modify the custom view
    /*  val yValsCandleStick = ArrayList<CandleEntry>()
      yValsCandleStick.add(CandleEntry(0f, 225.0f, 219.84f, 224.94f, 221.07f))
      yValsCandleStick.add(CandleEntry(1f, 228.35f, 222.57f, 223.52f, 226.41f))
      yValsCandleStick.add(CandleEntry(4f, 228.35f, 222.57f, 223.52f, 226.41f))
      yValsCandleStick.add(CandleEntry(5f, 228.35f, 222.57f, 223.52f, 226.41f))
      yValsCandleStick.add(CandleEntry(2f, 226.84f, 222.52f, 225.75f, 223.84f))
      yValsCandleStick.add(CandleEntry(3f, 222.95f, 217.27f, 222.15f, 217.88f))
*/
    /* if (emptyList().isNotEmpty()) {
         val stock = Stock()
         val scaffoldState = rememberScaffoldState()
         AppTheme(
             darkTheme = isDarkTheme,
             isNetworkAvailable = isNetworkAvailable,
             scaffoldState = scaffoldState
         ) {
             Scaffold {
                 Column(
                     modifier = Modifier.fillMaxWidth()

                 ) {
                     Text(
                         modifier = Modifier
                             .align(Alignment.CenterHorizontally),
                         style = MaterialTheme.typography.h4,
                         color = MaterialTheme.colors.onPrimary,
                         text = "$" + String.format("%.2f", (stock.last))
                     )
                     Text(
                         modifier = Modifier
                             .align(Alignment.CenterHorizontally),
                         style = MaterialTheme.typography.subtitle1,
                         text = "$" + String.format("%.2f", (stock.last - stock.prevClose)),
                         color = if (stock.prevClose > stock.last) {
                             Color.Red
                         } else {
                             Color.Green
                         }
                     )
                     Row(
                         modifier = Modifier.fillMaxWidth(),
                         horizontalArrangement = SpaceAround
                     ) {
                         Button(onClick = {}) {
                             Text(text = "Buy")
                         }
                         Button(onClick = {}) {
                             Text(text = "Sell")
                         }
                     }
                 }
             }
         }
     }*/
}
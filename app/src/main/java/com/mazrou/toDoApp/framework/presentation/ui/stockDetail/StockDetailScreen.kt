package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme

@ExperimentalMaterialApi
@Composable
fun StockDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    stocks: List<Stock>
) {
    if (stocks.isNotEmpty()) {
        val stock = stocks[0]
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
    }
}
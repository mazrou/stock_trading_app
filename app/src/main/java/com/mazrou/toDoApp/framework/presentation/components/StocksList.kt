package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.framework.presentation.navigation.Screen

@ExperimentalUnitApi
@Composable
fun StocksList(
    loading: Boolean,
    stocks: List<Stock>,
    onNavigateToStockDetailScreen: (String) -> Unit,
) {
    CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 20f )
    if (loading && stocks.isEmpty()){
        //TODO show the shimmer
    }
    else if (stocks.isEmpty()){
        NothingHere()
    }else{
        LazyColumn {
            itemsIndexed(items = stocks) { _: Int, item: Stock ->
                StockItem(stock = item) {
                    val route = Screen.StocksDetails.route + "/${item.ticker}"
                    onNavigateToStockDetailScreen(route)
                }
            }
        }
    }
}
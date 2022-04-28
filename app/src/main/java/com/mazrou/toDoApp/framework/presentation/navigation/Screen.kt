package com.mazrou.toDoApp.framework.presentation.navigation

sealed class Screen(
    val route : String
){
    object StocksList  : Screen(route = "stocksList")
    object StocksDetails  : Screen(route = "StocksDetails")
    object TradeHistory  : Screen(route = "TradeHistory")
}

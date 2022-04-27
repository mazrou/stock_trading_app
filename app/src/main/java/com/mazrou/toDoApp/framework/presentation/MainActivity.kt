package com.mazrou.toDoApp.framework.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mazrou.toDoApp.framework.presentation.navigation.Screen
import com.mazrou.toDoApp.framework.presentation.ui.stockDetail.StockDetailScreen
import com.mazrou.toDoApp.framework.presentation.ui.stockDetail.StockDetailViewModel
import com.mazrou.toDoApp.framework.presentation.ui.stocksList.MainScreen
import com.mazrou.toDoApp.framework.presentation.ui.stocksList.StocksListViewModel
import com.mazrou.toDoApp.framework.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalUnitApi
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    //private val viewModel: StocksListViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.StocksList.route) {
                composable(route = Screen.StocksList.route) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: StocksListViewModel =
                        viewModel(key = "StocksListViwModel", factory = factory)
                    MainScreen(
                        isDarkTheme = true,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        viewModel = viewModel,
                        onNavigateToStockDetailScreen = navController::navigate,
                    )
                }
                composable(
                    route = Screen.StocksDetails.route + "/{stockTicket}/{last}/{previousClose}",
                    arguments = listOf(navArgument("stockTicket") {
                        type = NavType.StringType
                    },
                        navArgument("last") {
                            type = NavType.FloatType
                        },
                        navArgument("previousClose") {
                            type = NavType.FloatType
                        })
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: StockDetailViewModel =
                        viewModel(key = "StockDetailViwModel", factory = factory)
                    StockDetailScreen(
                        isDarkTheme = true,
                        isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
                        ticker = navBackStackEntry.arguments?.getString("stockTicket"),
                        viewModel = viewModel,
                        last = navBackStackEntry.arguments?.getFloat("last")?.toDouble(),
                        previousClose = navBackStackEntry.arguments?.getFloat("previousClose")
                            ?.toDouble(),
                    )
                }
            }
        }
    }
}


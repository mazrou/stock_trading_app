package com.mazrou.toDoApp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.ui.AppBarConfiguration
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme
import com.mazrou.toDoApp.business.domain.models.Stock
import com.mazrou.toDoApp.databinding.ActivityMainBinding
import com.mazrou.toDoApp.framework.presentation.StocksEvent
import com.mazrou.toDoApp.framework.presentation.StocksListViewModel
import com.mazrou.toDoApp.framework.presentation.components.StockItem
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme
import com.mazrou.toDoApp.framework.presentation.util.ConnectivityManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    private val viewModel: StocksListViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

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
            AppCompatTheme() {
                val stocks = viewModel.state.value.stocks
                stocks?.let {
                    mainScreen(stocks = it)
                }
            }
        }


        /*binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        } */
    }

    @Composable
    // @PreviewParameter(provider = List<Stock>.javaClass)

    fun mainScreen(stocks: List<Stock>) {

        val scaffoldState = rememberScaffoldState()

        AppTheme(
            darkTheme = false,
            isNetworkAvailable = connectivityManager.isNetworkAvailable.value,
            scaffoldState = scaffoldState
        ) {
            LazyColumn {
                itemsIndexed(items = stocks) { index: Int, item: Stock ->
                    StockItem(stock = item) {
                        viewModel.onTriggerEvent(
                            StocksEvent.GetStocksFromNetwork(
                                listOf("FBC", "AAPL","SPY","MCF","GLG")
                            )
                        )
                    }
                }
            }
        }
    }

/*

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}
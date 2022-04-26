package com.mazrou.toDoApp.framework.presentation.ui.stockDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.CandleEntry
import com.mazrou.toDoApp.framework.presentation.components.CandleStickChartView


@ExperimentalMaterialApi
@Composable
fun StockDetailScreen(
    isDarkTheme: Boolean,
    isNetworkAvailable: Boolean,
    viewModel: StockDetailViwModel
) {

    //CandleStickChart(LocalContext.current)
    // If the state updates
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        // Modify the custom view
        val yValsCandleStick = ArrayList<CandleEntry>()
        yValsCandleStick.add(CandleEntry(0f, 225.0f, 219.84f, 224.94f, 221.07f))
        yValsCandleStick.add(CandleEntry(1f, 228.35f, 222.57f, 223.52f, 226.41f))
        yValsCandleStick.add(CandleEntry(4f, 228.35f, 222.57f, 223.52f, 226.41f))
        yValsCandleStick.add(CandleEntry(5f, 228.35f, 222.57f, 223.52f, 226.41f))
        yValsCandleStick.add(CandleEntry(2f, 226.84f, 222.52f, 225.75f, 223.84f))
        yValsCandleStick.add(CandleEntry(3f, 222.95f, 217.27f, 222.15f, 217.88f))

        CandleStickChartView(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight(.5f)
                .fillMaxWidth(.9f)
                .align(Alignment.CenterHorizontally),
            data = yValsCandleStick,
            ticker = "AAB"
        )
    }

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
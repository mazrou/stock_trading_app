package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.business.domain.models.Stock

@Composable
fun StockItem(
    stock: Stock,
    onClick: () -> Unit
) {
    Card(
       // shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .clickable { onClick() },
        elevation = 2.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 16.dp , vertical = 5.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // Stock ticker
            Column(
                modifier = Modifier.align(CenterVertically),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = stock.ticker,
                    style = MaterialTheme.typography.h6,
                    color = Color.DarkGray

                )
                Text(
                    text = stock.ticker,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.LightGray
                )
            }
            //Pricing
            Column(
                modifier = Modifier.align(CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier  =Modifier.align(Alignment.End),
                    text = stock.last.toString() + " $",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.DarkGray

                )
                Text(
                    modifier  =Modifier.align(Alignment.CenterHorizontally),
                    text = String.format("%.1f", (stock.last - stock.prevClose)) + "%",
                    style = MaterialTheme.typography.subtitle2,
                    color = if (stock.prevClose > stock.last) {
                        Color.Red
                    } else {
                        Color.Green
                    }
                )
            }
        }
    }
}
package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.business.domain.models.Trade
import com.mazrou.toDoApp.business.domain.models.TradingType
import java.time.format.DateTimeFormatter

@Composable
fun TradeItem(
    trade: Trade
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .border(1.dp, MaterialTheme.colors.secondary)
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // Stock ticker
        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = trade.ticker,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = trade.type.name,
                style = MaterialTheme.typography.subtitle2,
                color = if (trade.type == TradingType.SELL) {
                    Color.Red
                } else {
                    Color.Green
                }
            )
        }
        //Pricing
        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.align(Alignment.End),
                text = "$ " + String.format("%.1f", trade.price.toString()),
                style = MaterialTheme.typography.subtitle1,
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${trade.date.hour}:${trade.date.minute}",
                style = MaterialTheme.typography.subtitle2,
            )
        }
    }
}
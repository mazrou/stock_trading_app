package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.ExperimentalUnitApi
import com.mazrou.toDoApp.business.domain.models.Trade

@ExperimentalUnitApi
@Composable
fun TradeList(
    loading: Boolean,
    trades: List<Trade>,
    modifier: Modifier = Modifier
) {
    if (loading && trades.isEmpty()) {
        //TODO show the shimmer
    } else if (trades.isEmpty()) {
        NothingHere()
    } else {
        //FIXME this list is laggy . this is no my problem
        LazyColumn(modifier) {
            itemsIndexed(items = trades) { _: Int, item: Trade ->
                TradeItem(trade = item)
            }
        }
    }
}
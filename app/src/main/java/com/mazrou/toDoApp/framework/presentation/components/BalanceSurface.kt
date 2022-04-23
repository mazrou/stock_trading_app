package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mazrou.toDoApp.framework.presentation.theme.Gray

@Composable
fun BalanceSurface(
    balance : Double
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colors.surface),
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp,

        ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Main balance",
                modifier = Modifier
                    .padding(16.dp, 16.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.subtitle1,
                color = Gray
            )
            Text(
                text = "$${String.format("%.2f",balance)}",
                modifier = Modifier
                    .padding(0.dp, 16.dp, 16.dp, 46.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h3,
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
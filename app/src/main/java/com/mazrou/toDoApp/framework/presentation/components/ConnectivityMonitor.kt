package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.mazrou.toDoApp.framework.presentation.theme.AppTheme
import com.mazrou.toDoApp.framework.presentation.theme.RedErrorLight

@Composable
fun ConnectivityMonitor(
    isNetworkAvailable: Boolean,
    backgroundColor : Color
){
    if(!isNetworkAvailable){
        Column(modifier = Modifier.fillMaxWidth().background(backgroundColor)){
            Text(
                "No network connection",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp),
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
    }
}
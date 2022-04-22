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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .padding(5.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.5.sp,
                    letterSpacing = 0.1.sp
                ),
                color = Color.White
            )
        }
    }
}
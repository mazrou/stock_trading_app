package com.mazrou.toDoApp.framework.presentation.components

import android.graphics.Color
import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry

@Composable
fun CandleStickChartView(
    modifier: Modifier,
    data: MutableList<CandleEntry>,
    ticker: String
) {
    AndroidView(
        factory = ::CandleStickChart,
        modifier = modifier,
    ) { candleStickChart ->

        val set1 = CandleDataSet(data, "$ticker - to day (last 5 hours) ").apply {
            color = Color.rgb(80, 80, 80)
            shadowColor = Color.WHITE
            shadowWidth = 0.8f
            decreasingColor = Color.RED
            decreasingPaintStyle = Paint.Style.FILL
            increasingColor = Color.GREEN
            increasingPaintStyle = Paint.Style.FILL
            neutralColor = Color.LTGRAY
            setDrawValues(true)
        }

        candleStickChart.apply {
            isHighlightPerDragEnabled = true
            setDrawBorders(true)
            setBorderColor(Color.GRAY)
            requestDisallowInterceptTouchEvent(true)

            // axisLeft
            axisLeft.run {
                setDrawLabels(false)
                setDrawGridLines(false)
            }

            // axisLeft
            axisRight.run {
                setDrawGridLines(false)
                textColor = Color.WHITE
            }

            //xAxis
            xAxis.run {
                setDrawGridLines(false) // disable x axis grid lines
                setDrawLabels(false)
                setDrawLabels(false)
                granularity = 1f
                isGranularityEnabled = true
                setAvoidFirstLastClipping(true)
            }
            legend.run {
                isEnabled = true
                textColor = Color.WHITE
            }
            this.data = CandleData(set1)
            invalidate()
        }
    }
}
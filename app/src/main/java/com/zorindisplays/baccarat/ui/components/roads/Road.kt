package com.zorindisplays.baccarat.ui.components.roads

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

@Composable
fun Road(
    centerX: Float = 960f,
    topY: Float = 185f,
    cellSize: Float = 46f,
    colCount: Int = 40,
    color: Color = BaccaratTheme.colors.roadBackground
) {
    Canvas(modifier = Modifier.fillMaxSize()) {

        val width = colCount * cellSize
        val height = 6f * cellSize

        val left = centerX - width / 2f
        val top = topY

        drawRect(
            color = color,
            topLeft = Offset(left, top),
            size = Size(width, height)
        )
    }
}

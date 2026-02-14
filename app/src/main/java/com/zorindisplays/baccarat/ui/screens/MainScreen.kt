package com.zorindisplays.baccarat.ui.screens

import androidx.compose.runtime.Composable
import com.zorindisplays.baccarat.ui.components.roads.Road
import com.zorindisplays.baccarat.ui.components.sections.MinMaxSection
import com.zorindisplays.baccarat.ui.components.sections.TableShoeHandSection

@Composable
fun MainScreen() {
    Road(
        centerX = 960f,
        topY = 185f,
        cellSize = 46f,
        colCount = 40
    )
    Road(
        centerX = 477f,
        topY = 562f,
        cellSize = 73f,
        colCount = 12
    )
    Road(
        centerX = 1534f,
        topY = 562f,
        cellSize = 23f,
        colCount = 30
    )
    Road(
        centerX = 1534f,
        topY = 712f,
        cellSize = 23f,
        colCount = 30
    )
    Road(
        centerX = 1534f,
        topY = 862f,
        cellSize = 23f,
        colCount = 30
    )
    MinMaxSection()
    TableShoeHandSection()
}

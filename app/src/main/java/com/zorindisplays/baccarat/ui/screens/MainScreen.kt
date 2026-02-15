package com.zorindisplays.baccarat.ui.screens

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zorindisplays.baccarat.ui.components.roads.BeadResult
import com.zorindisplays.baccarat.ui.components.roads.BeadRoadItem
import com.zorindisplays.baccarat.ui.components.roads.Road
import com.zorindisplays.baccarat.ui.components.sections.MinMaxSection
import com.zorindisplays.baccarat.ui.components.sections.PairSection
import com.zorindisplays.baccarat.ui.components.sections.PredictionSection
import com.zorindisplays.baccarat.ui.components.sections.ResultSection
import com.zorindisplays.baccarat.ui.components.sections.TableShoeHandSection
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme

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
    ResultSection()
    PairSection()
    PredictionSection()
}

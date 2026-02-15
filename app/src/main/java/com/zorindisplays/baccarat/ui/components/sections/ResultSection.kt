package com.zorindisplays.baccarat.ui.components.sections

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.zorindisplays.baccarat.ui.components.roads.BeadResult
import com.zorindisplays.baccarat.ui.components.roads.BeadRoadItem
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun ResultSection(
    startX: Float = 40f,
    startY: Float = 461f,
    width: Float = 876f,
    height: Float = 101f,

    bankerScore: Int = 0,
    tieScore: Int = 0,
    playerScore: Int = 0,

    columnInnerPaddingPx: Float = 8f,
    iconTextGapPx: Float = 10f,
    beadCellSizePx: Float = 73f,

    // offsets
    titleYOffsetPx: Float = -12f,
    valueYOffsetPx: Float = 8f,

    // ✅ цвета
    titleColor: Color = BaccaratTheme.colors.textSecondaryColor,
    valueColor: Color = BaccaratTheme.colors.textPrimaryColor
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float): Dp = with(density) { px.toDp() }

    Box(
        modifier = Modifier
            .offset { IntOffset(startX.roundToInt(), startY.roundToInt()) }
            .requiredSize(pxToDp(width), pxToDp(height))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ResultSlot(
                title = "BANKER",
                score = bankerScore,
                result = BeadResult.BANKER,
                beadSizePx = beadCellSizePx,
                gapPx = iconTextGapPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )

            ResultSlot(
                title = "TIE",
                score = tieScore,
                result = BeadResult.TIE,
                beadSizePx = beadCellSizePx,
                gapPx = iconTextGapPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )

            ResultSlot(
                title = "PLAYER",
                score = playerScore,
                result = BeadResult.PLAYER,
                beadSizePx = beadCellSizePx,
                gapPx = iconTextGapPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )
        }
    }
}

@Composable
private fun RowScope.ResultSlot(
    title: String,
    score: Int,
    result: BeadResult,
    beadSizePx: Float,
    gapPx: Float,
    titleYOffsetPx: Float,
    valueYOffsetPx: Float,
    titleColor: Color,
    valueColor: Color
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float) = with(density) { px.toDp() }

    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = when (result) {
            BeadResult.BANKER -> Alignment.CenterStart
            BeadResult.PLAYER -> Alignment.CenterEnd
            else -> Alignment.Center
        }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            BeadRoadItem(
                modifier = Modifier.size(pxToDp(beadSizePx)),
                result = result,
                isBankerPair = false,
                isPlayerPair = false,
                isNatural = false,
                resultScore = null,
                showPairs = false,
                showNatural = false,
                cellBackgroundColor = BaccaratTheme.colors.screenBackground
            )

            Spacer(Modifier.width(pxToDp(gapPx)))

            PinnedTopBottomText(
                modifier = Modifier.height(pxToDp(beadSizePx)),
                topText = title,
                bottomText = score.toString(),
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )
        }
    }
}

@Composable
private fun PinnedTopBottomText(
    modifier: Modifier,
    topText: String,
    bottomText: String,
    titleYOffsetPx: Float,
    valueYOffsetPx: Float,
    titleColor: Color,
    valueColor: Color
) {
    val titleStyle = BaccaratTheme.typography.resultTitle.copy(color = titleColor)
    val valueStyle = BaccaratTheme.typography.resultValue.copy(color = valueColor)

    Layout(
        modifier = modifier,
        content = {
            BasicText(text = topText, style = titleStyle)
            BasicText(text = bottomText, style = valueStyle)
        }
    ) { measurables, constraints ->

        val title = measurables[0].measure(constraints.copy(minHeight = 0))
        val value = measurables[1].measure(constraints.copy(minHeight = 0))

        val w = max(title.width, value.width)
        val h = constraints.maxHeight

        layout(w, h) {
            val titleY = titleYOffsetPx.roundToInt()
            val valueY = h - value.height + valueYOffsetPx.roundToInt()

            title.placeRelative(0, titleY)
            value.placeRelative(0, valueY)
        }
    }
}

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
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun PairSection(
    startX: Float = 1190f,
    startY: Float = 461f,
    width: Float = 690f,
    height: Float = 101f,

    bankerPairCount: Int = 0,
    naturalCount: Int = 0,
    playerPairCount: Int = 0,

    // “виртуальная высота иконки” для совпадения уровней с ResultSection
    anchorHeightPx: Float = 73f,

    // offsets (те же, что ты подогнал)
    titleYOffsetPx: Float = -12f,
    valueYOffsetPx: Float = 8f,

    // padding для центра/права (левому сделаем 0)
    columnInnerPaddingPx: Float = 8f,

    // цвета
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

            // LEFT: без отступов, прям в левый край секции
            PairSlot(
                title = "BANKER PAIR",
                value = bankerPairCount.toString(),
                alignment = Alignment.CenterStart,
                horizontalPaddingPx = 0f,
                anchorHeightPx = anchorHeightPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )

            // CENTER
            PairSlot(
                title = "NATURAL",
                value = naturalCount.toString(),
                alignment = Alignment.Center,
                horizontalPaddingPx = columnInnerPaddingPx,
                anchorHeightPx = anchorHeightPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )

            // RIGHT
            PairSlot(
                title = "PLAYER PAIR",
                value = playerPairCount.toString(),
                alignment = Alignment.CenterEnd,
                horizontalPaddingPx = columnInnerPaddingPx,
                anchorHeightPx = anchorHeightPx,
                titleYOffsetPx = titleYOffsetPx,
                valueYOffsetPx = valueYOffsetPx,
                titleColor = titleColor,
                valueColor = valueColor
            )
        }
    }
}

@Composable
private fun RowScope.PairSlot(
    title: String,
    value: String,
    alignment: Alignment,
    horizontalPaddingPx: Float,
    anchorHeightPx: Float,
    titleYOffsetPx: Float,
    valueYOffsetPx: Float,
    titleColor: Color,
    valueColor: Color
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float) = with(density) { px.toDp() }

    Box(
        modifier = Modifier
            .weight(1f)
            .fillMaxHeight()
            .padding(horizontal = pxToDp(horizontalPaddingPx)),
        contentAlignment = alignment
    ) {
        // ВАЖНО: высоту берём не секции, а anchorHeight (73px), и центрируем по вертикали
        PinnedTopBottomText(
            modifier = Modifier.height(pxToDp(anchorHeightPx)),
            topText = title,
            bottomText = value,
            titleYOffsetPx = titleYOffsetPx,
            valueYOffsetPx = valueYOffsetPx,
            titleColor = titleColor,
            valueColor = valueColor
        )
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
            .coerceIn(constraints.minWidth, constraints.maxWidth)

        val h = constraints.maxHeight

        layout(w, h) {
            val titleY = titleYOffsetPx.roundToInt()
            val valueY = h - value.height + valueYOffsetPx.roundToInt()

            title.placeRelative(0, titleY)
            value.placeRelative(0, valueY)
        }
    }
}

package com.zorindisplays.baccarat.ui.components.sections

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
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
    valueColor: Color = BaccaratTheme.colors.textPrimaryColor,

    // ✅ кольцо слева от значения
    ringRadiusPx: Float = 17f,
    ringStrokeWidthPx: Float = 6f,
    ringGapPx: Float = 10f,

    // ✅ ручная подстройка вертикального центра кольца внутри строки значения
    ringCenterYOffsetPx: Float = 7f
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
                valueColor = valueColor,
                ringRadiusPx = ringRadiusPx,
                ringStrokeWidthPx = ringStrokeWidthPx,
                ringGapPx = ringGapPx,
                ringCenterYOffsetPx = ringCenterYOffsetPx
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
                valueColor = valueColor,
                ringRadiusPx = ringRadiusPx,
                ringStrokeWidthPx = ringStrokeWidthPx,
                ringGapPx = ringGapPx,
                ringCenterYOffsetPx = ringCenterYOffsetPx
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
                valueColor = valueColor,
                ringRadiusPx = ringRadiusPx,
                ringStrokeWidthPx = ringStrokeWidthPx,
                ringGapPx = ringGapPx,
                ringCenterYOffsetPx = ringCenterYOffsetPx
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
    valueColor: Color,
    ringRadiusPx: Float,
    ringStrokeWidthPx: Float,
    ringGapPx: Float,
    ringCenterYOffsetPx: Float
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
        // высоту берём не секции, а anchorHeight (73px), и центрируем по вертикали
        PinnedTopBottomText(
            modifier = Modifier.height(pxToDp(anchorHeightPx)),
            topText = title,
            bottomText = value,
            titleYOffsetPx = titleYOffsetPx,
            valueYOffsetPx = valueYOffsetPx,
            titleColor = titleColor,
            valueColor = valueColor,
            ringRadiusPx = ringRadiusPx,
            ringStrokeWidthPx = ringStrokeWidthPx,
            ringGapPx = ringGapPx,
            ringCenterYOffsetPx = ringCenterYOffsetPx
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
    valueColor: Color,
    ringRadiusPx: Float,
    ringStrokeWidthPx: Float,
    ringGapPx: Float,
    ringCenterYOffsetPx: Float
) {
    val titleStyle = BaccaratTheme.typography.resultTitle.copy(color = titleColor)
    val valueStyle = BaccaratTheme.typography.resultValue.copy(color = valueColor)

    Layout(
        modifier = modifier,
        content = {
            // 0: top
            BasicText(text = topText, style = titleStyle)

            // 1: bottom (ring + value) with manual ring center offset
            Layout(
                content = {
                    RingIcon(
                        radiusPx = ringRadiusPx,
                        strokeWidthPx = ringStrokeWidthPx,
                        color = titleColor
                    )
                    BasicText(text = bottomText, style = valueStyle)
                }
            ) { measurables, constraints ->

                val ring = measurables[0].measure(constraints)
                val text = measurables[1].measure(constraints)

                val width = ring.width + ringGapPx.roundToInt() + text.width
                val height = max(ring.height, text.height)

                layout(width, height) {
                    // центр кольца относительно верхнего края строки (+ ручная подстройка)
                    val ringCenterY = height / 2f + ringCenterYOffsetPx
                    val ringY = (ringCenterY - ring.height / 2f).roundToInt()

                    ring.placeRelative(0, ringY)
                    text.placeRelative(ring.width + ringGapPx.roundToInt(), 0)
                }
            }
        }
    ) { measurables, constraints ->

        val title = measurables[0].measure(constraints.copy(minHeight = 0))
        val bottomRow = measurables[1].measure(constraints.copy(minHeight = 0))

        val w = max(title.width, bottomRow.width)
            .coerceIn(constraints.minWidth, constraints.maxWidth)

        val h = constraints.maxHeight

        layout(w, h) {
            val titleY = titleYOffsetPx.roundToInt()
            val valueY = h - bottomRow.height + valueYOffsetPx.roundToInt()

            title.placeRelative(0, titleY)
            bottomRow.placeRelative(0, valueY)
        }
    }
}

@Composable
private fun RingIcon(
    radiusPx: Float,
    strokeWidthPx: Float,
    color: Color
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float) = with(density) { px.toDp() }

    // Stroke рисуется по центру, внешний радиус = R + W/2
    val outer = radiusPx + strokeWidthPx / 2f
    val sizeDp = pxToDp(outer * 2f)

    Canvas(modifier = Modifier.size(sizeDp)) {
        drawCircle(
            color = color,
            radius = radiusPx,
            center = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height / 2f),
            style = Stroke(width = strokeWidthPx)
        )
    }
}

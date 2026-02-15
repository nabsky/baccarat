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
import androidx.compose.ui.geometry.Offset
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.max
import kotlin.math.roundToInt

private enum class PairBadgeType { BANKER, NATURAL, PLAYER }

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

    // offsets
    titleYOffsetPx: Float = -12f,
    valueYOffsetPx: Float = 8f,

    // padding для центра/права (левому сделаем 0)
    columnInnerPaddingPx: Float = 8f,

    // цвета текста
    titleColor: Color = BaccaratTheme.colors.textSecondaryColor,
    valueColor: Color = BaccaratTheme.colors.textPrimaryColor,

    // кольцо слева от значения
    ringRadiusPx: Float = 17f,
    ringStrokeWidthPx: Float = 6f,
    ringGapPx: Float = 10f,

    // ручная подстройка вертикального центра кольца внутри строки значения
    ringCenterYOffsetPx: Float = 7f,

    // badge (маленький кружок “пары” как в BeadRoadItem)
    badgeRadiusPx: Float = 10f,
    badgeStrokeWidthPx: Float = 5f,

    bankerColor: Color = BaccaratTheme.colors.bankerColor,
    playerColor: Color = BaccaratTheme.colors.playerColor,
    naturalColor: Color = BaccaratTheme.colors.naturalColor,

    // ✅ stroke кружка = фон экрана
    badgeStrokeColor: Color = BaccaratTheme.colors.screenBackground
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
                ringCenterYOffsetPx = ringCenterYOffsetPx,
                badgeType = PairBadgeType.BANKER,
                badgeRadiusPx = badgeRadiusPx,
                badgeStrokeWidthPx = badgeStrokeWidthPx,
                bankerColor = bankerColor,
                playerColor = playerColor,
                naturalColor = naturalColor,
                badgeStrokeColor = badgeStrokeColor
            )

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
                ringCenterYOffsetPx = ringCenterYOffsetPx,
                badgeType = PairBadgeType.NATURAL,
                badgeRadiusPx = badgeRadiusPx,
                badgeStrokeWidthPx = badgeStrokeWidthPx,
                bankerColor = bankerColor,
                playerColor = playerColor,
                naturalColor = naturalColor,
                badgeStrokeColor = badgeStrokeColor
            )

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
                ringCenterYOffsetPx = ringCenterYOffsetPx,
                badgeType = PairBadgeType.PLAYER,
                badgeRadiusPx = badgeRadiusPx,
                badgeStrokeWidthPx = badgeStrokeWidthPx,
                bankerColor = bankerColor,
                playerColor = playerColor,
                naturalColor = naturalColor,
                badgeStrokeColor = badgeStrokeColor
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
    ringCenterYOffsetPx: Float,
    badgeType: PairBadgeType,
    badgeRadiusPx: Float,
    badgeStrokeWidthPx: Float,
    bankerColor: Color,
    playerColor: Color,
    naturalColor: Color,
    badgeStrokeColor: Color
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
            ringCenterYOffsetPx = ringCenterYOffsetPx,
            badgeType = badgeType,
            badgeRadiusPx = badgeRadiusPx,
            badgeStrokeWidthPx = badgeStrokeWidthPx,
            bankerColor = bankerColor,
            playerColor = playerColor,
            naturalColor = naturalColor,
            badgeStrokeColor = badgeStrokeColor
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
    ringCenterYOffsetPx: Float,
    badgeType: PairBadgeType,
    badgeRadiusPx: Float,
    badgeStrokeWidthPx: Float,
    bankerColor: Color,
    playerColor: Color,
    naturalColor: Color,
    badgeStrokeColor: Color
) {
    val titleStyle = BaccaratTheme.typography.resultTitle.copy(color = titleColor)
    val valueStyle = BaccaratTheme.typography.resultValue.copy(color = valueColor)

    Layout(
        modifier = modifier,
        content = {
            BasicText(text = topText, style = titleStyle)

            // bottom (ring + badge + value)
            Layout(
                content = {
                    RingWithBadgeIcon(
                        ringRadiusPx = ringRadiusPx,
                        ringStrokeWidthPx = ringStrokeWidthPx,
                        ringColor = titleColor,
                        badgeType = badgeType,
                        badgeRadiusPx = badgeRadiusPx,
                        badgeStrokeWidthPx = badgeStrokeWidthPx,
                        bankerColor = bankerColor,
                        playerColor = playerColor,
                        naturalColor = naturalColor,
                        badgeStrokeColor = badgeStrokeColor
                    )
                    BasicText(text = bottomText, style = valueStyle)
                }
            ) { measurables, constraints ->
                val icon = measurables[0].measure(constraints)
                val text = measurables[1].measure(constraints)

                val width = icon.width + ringGapPx.roundToInt() + text.width
                val height = max(icon.height, text.height)

                layout(width, height) {
                    val iconCenterY = height / 2f + ringCenterYOffsetPx
                    val iconY = (iconCenterY - icon.height / 2f).roundToInt()

                    icon.placeRelative(0, iconY)
                    text.placeRelative(icon.width + ringGapPx.roundToInt(), 0)
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
private fun RingWithBadgeIcon(
    ringRadiusPx: Float,
    ringStrokeWidthPx: Float,
    ringColor: Color,
    badgeType: PairBadgeType,
    badgeRadiusPx: Float,
    badgeStrokeWidthPx: Float,
    bankerColor: Color,
    playerColor: Color,
    naturalColor: Color,
    badgeStrokeColor: Color
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float) = with(density) { px.toDp() }

    // stroke по центру
    val ringOuter = ringRadiusPx + ringStrokeWidthPx / 2f
    val badgeOuter = badgeRadiusPx + badgeStrokeWidthPx / 2f

    // хотим, чтобы центр бейджа был на центральной линии stroke кольца => d = ringRadiusPx
    val d = ringRadiusPx

    // чтобы ничего не обрезалось, холст должен учитывать вылет бейджа
    val extent = max(ringOuter, d + badgeOuter) // максимальная "полурадиусная" граница
    val sizeDp = pxToDp(extent * 2f)

    Canvas(modifier = Modifier.size(sizeDp)) {
        val c = Offset(size.width / 2f, size.height / 2f)

        // кольцо
        drawCircle(
            color = ringColor,
            radius = ringRadiusPx,
            center = c,
            style = Stroke(width = ringStrokeWidthPx)
        )

        // правильный сдвиг по диагонали: dx=dy=d/√2
        val diag = (d / 1.41421356f)

        val badgeCenter = when (badgeType) {
            PairBadgeType.BANKER -> Offset(c.x - diag, c.y - diag)          // верх-лево на окружности
            PairBadgeType.PLAYER -> Offset(c.x + diag, c.y + diag)          // низ-право на окружности
            PairBadgeType.NATURAL -> c                                      // центр
        }

        val badgeFill = when (badgeType) {
            PairBadgeType.BANKER -> bankerColor
            PairBadgeType.PLAYER -> playerColor
            PairBadgeType.NATURAL -> naturalColor
        }

        // заливка
        drawCircle(
            color = badgeFill,
            radius = badgeRadiusPx,
            center = badgeCenter
        )
        // обводка = фон экрана
        drawCircle(
            color = badgeStrokeColor,
            radius = badgeRadiusPx,
            center = badgeCenter,
            style = Stroke(width = badgeStrokeWidthPx)
        )
    }
}

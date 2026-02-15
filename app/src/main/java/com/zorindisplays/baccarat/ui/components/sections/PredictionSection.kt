package com.zorindisplays.baccarat.ui.components.sections

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
@Composable
fun PredictionSection(
    startX: Float = 916f,
    startY: Float = 461f,
    width: Float = 274f,
    height: Float = 619f,

    // квадраты
    squareSize: Float = 90f,
    leftSquareTopLeft: Offset = Offset(35f, 11f),
    rightSquareTopLeft: Offset = Offset(149f, 11f),
    circleRadius: Float = 36f,

    // ленты
    ribbonSize: Size = Size(66f, 506f),
    leftRibbonTopLeft: Offset = Offset(47f, 101f),
    rightRibbonTopLeft: Offset = Offset(161f, 101f),
    ribbonNotchHeight: Float = 28f,

    bankerColor: Color = BaccaratTheme.colors.bankerColor,
    playerColor: Color = BaccaratTheme.colors.playerColor,
    circleColor: Color = BaccaratTheme.colors.roadBackground,

    centerTextStyle: TextStyle = BaccaratTheme.typography.beadRoadItem.copy(textAlign = TextAlign.Center),
    centerTextYOffset: Float = 2f
) {
    val density = LocalDensity.current
    fun pxToDp(px: Float): Dp = with(density) { px.toDp() }

    val tm = rememberTextMeasurer()

    Canvas(
        modifier = Modifier
            .offset { IntOffset(startX.roundToInt(), startY.roundToInt()) }
            .requiredSize(pxToDp(width), pxToDp(height))
    ) {

        fun drawSquareWithCircleAndLetter(
            topLeft: Offset,
            squareColor: Color,
            letter: String,
            letterColor: Color
        ) {
            drawRect(
                color = squareColor,
                topLeft = topLeft,
                size = Size(squareSize, squareSize)
            )

            val c = Offset(
                x = topLeft.x + squareSize / 2f,
                y = topLeft.y + squareSize / 2f
            )

            drawCircle(
                color = circleColor,
                radius = circleRadius,
                center = c
            )

            val layout = tm.measure(
                text = letter,
                style = centerTextStyle.copy(color = letterColor)
            )

            val x = c.x - layout.size.width / 2f

            val ascent = layout.firstBaseline
            val descent = layout.size.height - layout.firstBaseline
            val baselineY = c.y + (ascent - descent) / 2f
            val y = baselineY - layout.firstBaseline + centerTextYOffset

            drawText(layout, topLeft = Offset(x, y))
        }

        fun drawRibbonWithVBottom(
            topLeft: Offset,
            color: Color
        ) {
            val l = topLeft.x
            val t = topLeft.y
            val r = l + ribbonSize.width
            val b = t + ribbonSize.height

            val cx = (l + r) / 2f
            val apexY = b - ribbonNotchHeight

            // Фигура: прямоугольник, но низ "V" (углы -> вершина)
            val path = Path().apply {
                moveTo(l, t)          // верх-лево
                lineTo(r, t)          // верх-право
                lineTo(r, b)          // низ-право (угол)
                lineTo(cx, apexY)     // вершина выреза
                lineTo(l, b)          // низ-лево (угол)
                close()
            }

            drawPath(path = path, color = color)
        }

        // квадраты
        drawSquareWithCircleAndLetter(
            topLeft = leftSquareTopLeft,
            squareColor = bankerColor,
            letter = "B",
            letterColor = bankerColor
        )
        drawSquareWithCircleAndLetter(
            topLeft = rightSquareTopLeft,
            squareColor = playerColor,
            letter = "P",
            letterColor = playerColor
        )

        // ленты
        drawRibbonWithVBottom(topLeft = leftRibbonTopLeft, color = bankerColor)
        drawRibbonWithVBottom(topLeft = rightRibbonTopLeft, color = playerColor)
    }
}

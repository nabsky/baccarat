package com.zorindisplays.baccarat.ui.components.roads

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.min

enum class BeadResult { BANKER, PLAYER, TIE }

@OptIn(ExperimentalTextApi::class)
@Composable
fun BeadRoadItem(
    modifier: Modifier = Modifier,

    cellSize: Float = 73f,
    resultRadius: Float = 34f,
    pairRadius: Float = 10f,
    pairStrokeWidth: Float = 5f,

    result: BeadResult,
    isBankerPair: Boolean,
    isPlayerPair: Boolean,
    isNatural: Boolean,
    resultScore: Int?,
    showPairs: Boolean = true,
    showNatural: Boolean = true,

    bankerColor: Color = BaccaratTheme.colors.bankerColor,
    playerColor: Color = BaccaratTheme.colors.playerColor,
    tieColor: Color = BaccaratTheme.colors.tieColor,
    naturalColor: Color = BaccaratTheme.colors.naturalColor,

    pairStrokeColor: Color = BaccaratTheme.colors.roadBackground,
    cellBackgroundColor: Color = BaccaratTheme.colors.roadBackground,

    centerTextStyle: TextStyle = BaccaratTheme.typography.beadRoadItem.copy(textAlign = TextAlign.Center)
) {
    val tm = rememberTextMeasurer()

    Canvas(modifier = modifier) {
        // реальный размер канваса может отличаться — подстрахуемся
        val s = min(cellSize, min(size.width, size.height))
        val cellCenter = Offset(s / 2f, s / 2f)

        // фон ячейки (если фон рисуется отдельно — можешь убрать)
        drawRect(
            color = cellBackgroundColor,
            topLeft = Offset.Zero,
            size = Size(s, s)
        )

        // цвет результата
        val fill = when (result) {
            BeadResult.BANKER -> bankerColor
            BeadResult.PLAYER -> playerColor
            BeadResult.TIE -> tieColor
        }

        // круг результата по центру
        drawCircle(
            color = fill,
            radius = resultRadius,
            center = cellCenter
        )

        // чтобы край обводки пары НЕ выходил за ячейку:
        // stroke рисуется по центру, значит надо inset = stroke/2
        val inset = pairStrokeWidth / 2f
        val cornerCenter = pairRadius + inset

        // banker pair: верх-лево
        if (showPairs && isBankerPair) {
            val p = Offset(cornerCenter, cornerCenter)
            drawCircle(color = bankerColor, radius = pairRadius, center = p)
            drawCircle(
                color = pairStrokeColor,
                radius = pairRadius,
                center = p,
                style = Stroke(width = pairStrokeWidth)
            )
        }

        // player pair: низ-право
        if (showPairs && isPlayerPair) {
            val p = Offset(s - cornerCenter, s - cornerCenter)
            drawCircle(color = playerColor, radius = pairRadius, center = p)
            drawCircle(
                color = pairStrokeColor,
                radius = pairRadius,
                center = p,
                style = Stroke(width = pairStrokeWidth)
            )
        }

        // текст в центре: score или буква
        val symbol = when (result) {
            BeadResult.BANKER -> "B"
            BeadResult.PLAYER -> "P"
            BeadResult.TIE -> "T"
        }
        val centerText = resultScore?.coerceIn(0, 9)?.toString() ?: symbol
        val textColor = if (showNatural && isNatural) naturalColor else Color.White

        val layout = tm.measure(
            text = centerText,
            style = centerTextStyle.copy(color = textColor)
        )

        // X: обычный центр
        val x = cellCenter.x - layout.size.width / 2f

        // Y: оптическое центрирование по baseline (совместимо со старыми версиями)
        // ascent ~= firstBaseline, descent ~= height - firstBaseline
        val ascent = layout.firstBaseline
        val descent = layout.size.height - layout.firstBaseline
        val baselineY = cellCenter.y + (ascent - descent) / 2f

        // drawText ждёт topLeft, поэтому переносим baseline -> top
        val y = baselineY - layout.firstBaseline

        drawText(
            layout,
            topLeft = Offset(x, y)
        )
    }
}

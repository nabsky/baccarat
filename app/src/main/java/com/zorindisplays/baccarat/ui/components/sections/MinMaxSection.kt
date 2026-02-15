package com.zorindisplays.baccarat.ui.components.sections

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import kotlin.math.max

@OptIn(ExperimentalTextApi::class)
@Composable
fun MinMaxSection(
    startX: Float = 40f,
    startY: Float = 12f,
    width: Float = 804f,
    height: Float = 161f,

    leftColWidth: Float = 160f,
    cellInnerHPadding: Float = 12f,
    topBottomRowInset: Float = 6f,

    // Заголовки
    minLabel: String = "MIN",
    maxLabel: String = "MAX",
    bankerPlayerLabel: String = "BANKER / PLAYER",
    tieLabel: String = "TIE",
    pairLabel: String = "PAIR",

    // Значения
    minRowValues: List<String> = listOf("1", "10", "5"),
    maxRowValues: List<String> = listOf("100", "50", "75"),

    // Валюта
    currency: String = "€",
    thousandSeparator: Char = ' ',
    decimalSeparator: Char = ',',
    isCurrencyPrefix: Boolean = false,

    valueRectWidthFraction: Float = 0.72f,

    // Стили
    labelStyle: TextStyle = BaccaratTheme.typography.minMaxLabel,
    valueStyle: TextStyle = BaccaratTheme.typography.minMaxValue,

    // ✅ Цвета (как в ResultSection)
    labelColor: Color = BaccaratTheme.colors.textSecondaryColor,
    valueColor: Color = BaccaratTheme.colors.textPrimaryColor,

    // фон секции (если нужен)
    backgroundColor: Color = Color.Red
) {
    val tm = rememberTextMeasurer()

    Canvas(modifier = Modifier.fillMaxSize()) {

        // фон секции (у тебя он закомментирован — оставляю как опционально)
        /*
        drawRect(
            color = backgroundColor,
            topLeft = Offset(startX, startY),
            size = Size(width, height)
        )
        */

        val table = Rect(startX, startY, startX + width, startY + height)

        val col0W = leftColWidth.coerceIn(0f, width)
        val dynW = max(0f, width - col0W)
        val colW = dynW / 3f
        val rowH = height / 3f

        fun cellRect(row: Int, col: Int): Rect {
            val top = table.top + row * rowH
            val bottom = top + rowH
            val (l, r) = when (col) {
                0 -> table.left to (table.left + col0W)
                1 -> (table.left + col0W) to (table.left + col0W + colW)
                2 -> (table.left + col0W + colW) to (table.left + col0W + 2f * colW)
                else -> (table.left + col0W + 2f * colW) to table.right
            }
            return Rect(l, top, r, bottom)
        }

        fun rowTextArea(rect: Rect, row: Int): Rect =
            when (row) {
                0, 2 -> Rect(rect.left, rect.top + topBottomRowInset, rect.right, rect.bottom - topBottomRowInset)
                else -> rect
            }

        fun drawCentered(text: String, area: Rect, style: TextStyle, color: Color) {
            if (text.isEmpty()) return
            val layout = tm.measure(text = text, style = style)
            val x = area.left + (area.width - layout.size.width) / 2f
            val y = area.top + (area.height - layout.size.height) / 2f
            drawText(layout, topLeft = Offset(x, y), color = color)
        }

        fun drawLeft(text: String, area: Rect, style: TextStyle, color: Color) {
            if (text.isEmpty()) return
            val layout = tm.measure(text = text, style = style)
            val x = area.left + cellInnerHPadding
            val y = area.top + (area.height - layout.size.height) / 2f
            drawText(layout, topLeft = Offset(x, y), color = color)
        }

        /**
         * Значение — единая строка (number+currency или currency+number),
         * выравнивается по ПРАВОМУ краю большого прямоугольника,
         * а большой прямоугольник центрируется в ячейке.
         */
        fun drawValue(value: String, cell: Rect) {
            if (value.isEmpty() && currency.isEmpty()) return

            val area = cell
            val bigW = area.width * valueRectWidthFraction
            val bigLeft = area.left + (area.width - bigW) / 2f
            val bigRect = Rect(bigLeft, area.top, bigLeft + bigW, area.bottom)

            val text = if (isCurrencyPrefix) "$currency$value" else "$value$currency"
            val layout = tm.measure(text = text, style = valueStyle)

            val x = bigRect.right - layout.size.width
            val y = area.top + (area.height - layout.size.height) / 2f
            drawText(layout, topLeft = Offset(x, y), color = valueColor)
        }

        // Row 0
        run {
            drawCentered(bankerPlayerLabel, rowTextArea(cellRect(0, 1), 0), labelStyle, labelColor)
            drawCentered(tieLabel,          rowTextArea(cellRect(0, 2), 0), labelStyle, labelColor)
            drawCentered(pairLabel,         rowTextArea(cellRect(0, 3), 0), labelStyle, labelColor)
        }

        // Row 1
        run {
            drawLeft(minLabel, rowTextArea(cellRect(1, 0), 1), labelStyle, labelColor)

            val v1 = minRowValues.getOrNull(0).orEmpty()
            val v2 = minRowValues.getOrNull(1).orEmpty()
            val v3 = minRowValues.getOrNull(2).orEmpty()

            drawValue(v1, rowTextArea(cellRect(1, 1), 1))
            drawValue(v2, rowTextArea(cellRect(1, 2), 1))
            drawValue(v3, rowTextArea(cellRect(1, 3), 1))
        }

        // Row 2
        run {
            drawLeft(maxLabel, rowTextArea(cellRect(2, 0), 2), labelStyle, labelColor)

            val v1 = maxRowValues.getOrNull(0).orEmpty()
            val v2 = maxRowValues.getOrNull(1).orEmpty()
            val v3 = maxRowValues.getOrNull(2).orEmpty()

            drawValue(v1, rowTextArea(cellRect(2, 1), 2))
            drawValue(v2, rowTextArea(cellRect(2, 2), 2))
            drawValue(v3, rowTextArea(cellRect(2, 3), 2))
        }
    }
}

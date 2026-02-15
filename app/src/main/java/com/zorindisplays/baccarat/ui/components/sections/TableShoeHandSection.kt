package com.zorindisplays.baccarat.ui.components.sections

import androidx.compose.foundation.Canvas
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

@OptIn(ExperimentalTextApi::class)
@Composable
fun TableShoeHandSection(
    startX: Float = 1262f,
    startY: Float = 12f,
    width: Float = 618f,
    height: Float = 161f,

    topBottomRowInset: Float = 6f,

    tableLabel: String = "TABLE",
    shoeLabel: String = "SHOE",
    handLabel: String = "HAND",

    tableValue: String = "1",
    shoeValue: String = "0",
    handValue: String = "0",

    labelStyle: TextStyle = BaccaratTheme.typography.tableLabel,
    valueStyle: TextStyle = BaccaratTheme.typography.tableValue,

    // ✅ новые цвета
    labelColor: Color = BaccaratTheme.colors.textSecondaryColor,
    valueColor: Color = BaccaratTheme.colors.textPrimaryColor,

    backgroundColor: Color = Color.Red
) {
    val tm = rememberTextMeasurer()

    Canvas(modifier = Modifier) {

        /*
        drawRect(
            color = backgroundColor,
            topLeft = Offset(startX, startY),
            size = Size(width, height)
        )
        */

        val tableRect = Rect(startX, startY, startX + width, startY + height)

        val topH = height / 3f
        val colW = width / 3f

        fun topCell(col: Int): Rect {
            val l = tableRect.left + col * colW
            return Rect(l, tableRect.top, l + colW, tableRect.top + topH)
        }

        fun bottomCell(col: Int): Rect {
            val l = tableRect.left + col * colW
            return Rect(l, tableRect.top + topH, l + colW, tableRect.bottom)
        }

        fun insetTopRow(rect: Rect): Rect =
            Rect(rect.left, rect.top + topBottomRowInset, rect.right, rect.bottom - topBottomRowInset)

        fun drawCentered(text: String, area: Rect, style: TextStyle, color: Color) {
            if (text.isEmpty()) return
            val layout = tm.measure(text = text, style = style)
            val x = area.left + (area.width - layout.size.width) / 2f
            val y = area.top + (area.height - layout.size.height) / 2f
            drawText(layout, topLeft = Offset(x, y), color = color)
        }

        // верх
        drawCentered(tableLabel, insetTopRow(topCell(0)), labelStyle, labelColor)
        drawCentered(shoeLabel,  insetTopRow(topCell(1)), labelStyle, labelColor)
        drawCentered(handLabel,  insetTopRow(topCell(2)), labelStyle, labelColor)

        // низ
        drawCentered(tableValue, bottomCell(0), valueStyle, valueColor)
        drawCentered(shoeValue,  bottomCell(1), valueStyle, valueColor)
        drawCentered(handValue,  bottomCell(2), valueStyle, valueColor)
    }
}

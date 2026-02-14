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

@OptIn(ExperimentalTextApi::class)
@Composable
fun TableShoeHandSection(
    startX: Float = 1262f,
    startY: Float = 12f,
    width: Float = 618f,
    height: Float = 161f,

    topRowInset: Float = 6f,
    bottomRowInset: Float = 6f,

    tableLabel: String = "TABLE",
    shoeLabel: String = "SHOE",
    handLabel: String = "HAND",

    tableValue: String = "1",
    shoeValue: String = "7",
    handValue: String = "63",

    labelStyle: TextStyle = BaccaratTheme.typography.tableLabel,
    valueStyle: TextStyle = BaccaratTheme.typography.tableValue,
    textColor: Color = BaccaratTheme.colors.textPrimaryColor,

    // пока для отладки
    backgroundColor: Color = Color.Red
) {
    val tm = rememberTextMeasurer()

    Canvas(modifier = Modifier.fillMaxSize()) {

/*
        // фон секции
        drawRect(
            color = backgroundColor,
            topLeft = Offset(startX, startY),
            size = Size(width, height)
        )
*/

        val tableRect = Rect(startX, startY, startX + width, startY + height)

        val colW = width / 3f
        val rowH = height / 2f

        fun cellRect(row: Int, col: Int): Rect {
            val left = tableRect.left + col * colW
            val right = left + colW
            val top = tableRect.top + row * rowH
            val bottom = top + rowH
            return Rect(left, top, right, bottom)
        }

        fun insetRect(rect: Rect, top: Float = 0f, bottom: Float = 0f): Rect =
            Rect(rect.left, rect.top + top, rect.right, rect.bottom - bottom)

        fun drawCentered(text: String, area: Rect, style: TextStyle) {
            if (text.isEmpty()) return
            val layout = tm.measure(text = text, style = style)
            val x = area.left + (area.width - layout.size.width) / 2f
            val y = area.top + (area.height - layout.size.height) / 2f
            drawText(layout, topLeft = Offset(x, y), color = textColor)
        }

        // Row 0: labels
        val r0c0 = insetRect(cellRect(0, 0), top = topRowInset, bottom = topRowInset)
        val r0c1 = insetRect(cellRect(0, 1), top = topRowInset, bottom = topRowInset)
        val r0c2 = insetRect(cellRect(0, 2), top = topRowInset, bottom = topRowInset)

        drawCentered(tableLabel, r0c0, labelStyle)
        drawCentered(shoeLabel, r0c1, labelStyle)
        drawCentered(handLabel, r0c2, labelStyle)

        // Row 1: big values (centered under labels)
        val r1c0 = insetRect(cellRect(1, 0), top = bottomRowInset, bottom = bottomRowInset)
        val r1c1 = insetRect(cellRect(1, 1), top = bottomRowInset, bottom = bottomRowInset)
        val r1c2 = insetRect(cellRect(1, 2), top = bottomRowInset, bottom = bottomRowInset)

        drawCentered(tableValue, r1c0, valueStyle)
        drawCentered(shoeValue, r1c1, valueStyle)
        drawCentered(handValue, r1c2, valueStyle)
    }
}

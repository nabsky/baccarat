package com.zorindisplays.baccarat.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zorindisplays.baccarat.ui.components.AmountText
import com.zorindisplays.baccarat.ui.components.CurrencyPosition
import com.zorindisplays.baccarat.ui.components.MoneyFormat
import com.zorindisplays.baccarat.ui.components.VerticalAlign
import com.zorindisplays.baccarat.ui.theme.MontserratBold

@Composable
fun MainScreen() {
    AmountText(
        amount = 1234567.895,   // → 1 234 567,90
        //modifier = Modifier.requiredWidth(900.dp).requiredHeight(160.dp),
        modifier = Modifier.fillMaxSize(),
        style = TextStyle(
            fontFamily = MontserratBold,
            fontSize = 72.sp
        ),
        format = MoneyFormat(
            currency = "€",
            currencyPosition = CurrencyPosition.Prefix,
            thousandsSeparator = ' ',
            decimalSeparator = ',',
            fractionDigits = 2
        ),
        fillColor = Color(0xFFFFD54F),
        strokeColor = Color.Black,
        strokeWidth = 6.dp,
        textAlign = TextAlign.Center,
        verticalAlign = VerticalAlign.Center,
        opticalCentering = true
    )
}

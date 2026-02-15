package com.zorindisplays.baccarat.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.zorindisplays.baccarat.R

val BaseTextStyle = TextStyle(
    fontFamily = FontFamily(
        Font(R.font.montserrat_bold)
    )
)

fun baseTheme(): BaccaratThemeTokens = BaccaratThemeTokens(
    colors = BaccaratColors(
        screenBackground    = Color(0xFF0F172A),
        roadBackground      = Color(0xFFE6E6E6),
        bankerColor         = Color(0xFFC1121F),
        playerColor         = Color(0xFF005aa7),
        tieColor            = Color(0xFF2E7D32),
        naturalColor        = Color(0xFFFFD54F),
        textPrimaryColor    = Color(0xFFFFFFFF),
        textSecondaryColor  = Color(0xFFCCCCCC),
        logoPrimaryColor    = Color(0xFFFFD54F),
        logoSecondaryColor  = Color(0xFFFFFFFF)
    ),
    typography = BaccaratTypography(
        minMaxLabel     = BaseTextStyle.copy(fontSize = 20.sp),
        minMaxValue     = BaseTextStyle.copy(fontSize = 40.sp),
        tableLabel      = BaseTextStyle.copy(fontSize = 20.sp),
        tableValue      = BaseTextStyle.copy(fontSize = 80.sp),
        resultTitle     = BaseTextStyle.copy(fontSize = 20.sp),
        resultValue     = BaseTextStyle.copy(fontSize = 40.sp),
        pairTitle       = BaseTextStyle.copy(fontSize = 20.sp),
        pairValue       = BaseTextStyle.copy(fontSize = 40.sp),
        beadRoadItem    = BaseTextStyle.copy(fontSize = 30.sp),
    )
)

fun themeVariantWhite(base: BaccaratThemeTokens): BaccaratThemeTokens =
    base.copy(
        colors = base.colors.copy(
            roadBackground = Color(0xFFFFFFFF)
        )
    )

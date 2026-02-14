package com.zorindisplays.baccarat.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

private val LocalBaccaratTheme = staticCompositionLocalOf<BaccaratThemeTokens> {
    error("BaccaratThemeTokens is not provided")
}

object BaccaratTheme {
    val tokens: BaccaratThemeTokens
        @Composable get() = LocalBaccaratTheme.current

    val colors: BaccaratColors
        @Composable get() = tokens.colors

    val typography: BaccaratTypography
        @Composable get() = tokens.typography
}

@Composable
fun BaccaratThemeProvider(
    tokens: BaccaratThemeTokens = baseTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBaccaratTheme provides tokens,
        content = content
    )
}

@Immutable
data class BaccaratColors(
    val screenBackground: Color,
    val roadBackground: Color,

    val bankerColor: Color,
    val playerColor: Color,
    val tieColor: Color,
    val naturalColor: Color,

    val textPrimaryColor: Color,
    val textSecondaryColor: Color,

    val logoPrimaryColor: Color,
    val logoSecondaryColor: Color,
)

@Immutable
data class BaccaratTypography(
    val minMaxLabel: TextStyle,
    val minMaxValue: TextStyle,

    val tableLabel: TextStyle,
    val tableValue: TextStyle,

    val resultTitle: TextStyle,
    val resultValue: TextStyle,

    val pairTitle: TextStyle,
    val pairValue: TextStyle,
)

@Immutable
data class BaccaratThemeTokens(
    val colors: BaccaratColors,
    val typography: BaccaratTypography
)


package com.zorindisplays.baccarat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zorindisplays.baccarat.ui.screens.MainScreen
import com.zorindisplays.baccarat.ui.theme.BaccaratTheme
import com.zorindisplays.baccarat.ui.theme.BaccaratThemeProvider
import com.zorindisplays.baccarat.ui.theme.DefaultBackground
import com.zorindisplays.baccarat.ui.theme.baseTheme
import com.zorindisplays.baccarat.ui.theme.themeVariantWhite

@Composable
fun App() {
    val base = baseTheme()

    val tokens = themeVariantWhite(base)

    BaccaratThemeProvider(tokens) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BaccaratTheme.colors.screenBackground)
        ) {
            MainScreen()
        }
    }
}

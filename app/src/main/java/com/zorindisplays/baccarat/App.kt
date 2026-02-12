package com.zorindisplays.baccarat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zorindisplays.baccarat.ui.screens.MainScreen
import com.zorindisplays.baccarat.ui.theme.DefaultBackground

@Composable
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DefaultBackground)
    ) {
        MainScreen()
    }
}

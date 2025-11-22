package com.slyfly.repas.ui.theme

import androidx.compose.ui.graphics.Brush

fun functionGradientBlueToWhite(): Brush {
    val gradientBlueToWhite= Brush.verticalGradient(
        listOf(EndBlue, MiddleBlue, StartBlue),
        startY = 0.0f,
        endY = 5000.0f
    )
    return gradientBlueToWhite
}

fun functionAppBarGradientWhiteToBlue(): Brush {
    val gradient= Brush.verticalGradient(
        listOf(StartBlue, MiddleBlue, EndBlue),
        startY = 0.0f,
        endY = 300.0f
    )
    return gradient
}
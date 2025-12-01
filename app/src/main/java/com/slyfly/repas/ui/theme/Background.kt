package com.slyfly.repas.ui.theme

import androidx.compose.ui.graphics.Brush

fun functionGradientBlueToWhite(): Brush {
    return Brush.verticalGradient(
        listOf(EndBlue, MiddleBlue, StartBlue),
        startY = 0.0f,
        endY = 2500.0f
    )

}

fun functionAppBarGradientWhiteToBlue(): Brush {
    return Brush.verticalGradient(
        listOf(AppBarStartBlue, AppBarMiddleBlue, AppBarEndBlue),
        startY = 0.0f,
        endY = 300.0f
    )

}

fun gradientCurtainTop(): Brush {
    return Brush.verticalGradient(
        colors = listOf(EndBlue, MiddleBlue),
        startY = 0f,
        endY = 1250f
    )
}

fun gradientCurtainBottom(): Brush {
    return Brush.verticalGradient(
        colors = listOf(MiddleBlue, StartBlue),
        startY = 1250f,
        endY = 3000f

    )
}

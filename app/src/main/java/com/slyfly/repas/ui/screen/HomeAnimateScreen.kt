package com.slyfly.repas.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.slyfly.repas.ui.theme.gradientCurtainBottom
import com.slyfly.repas.ui.theme.gradientCurtainTop

@Composable

fun HomeAnimateScreen(){
    val height = remember { Animatable(400.dp, Dp.VectorConverter) }

    LaunchedEffect(Unit) {
        height.animateTo(
            targetValue = 0.dp,
            animationSpec = tween(
                durationMillis = 4000,
                easing = LinearEasing
            )
        )
    }
    Box(     modifier = Modifier
        .fillMaxSize()){


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.value)
            .align(Alignment.TopCenter)
            .background(gradientCurtainTop())
    ) {}


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.value)
            .align(Alignment.BottomCenter)
            .background(gradientCurtainBottom())
    ) {}
}
}
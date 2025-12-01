package com.slyfly.repas.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slyfly.repas.R
import com.slyfly.repas.ui.theme.dancingScript
import com.slyfly.repas.ui.theme.functionGradientBlueToWhite
import com.slyfly.repas.ui.theme.gradientCurtainBottom
import com.slyfly.repas.ui.theme.gradientCurtainTop


@SuppressLint("Range")
@Composable
fun Test() {


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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(functionGradientBlueToWhite())
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(contentAlignment = Alignment.Center) {

                Canvas(modifier = Modifier.size(110.dp)) {
                    drawArc(
                        color = Color.White,
                        startAngle = -60f,
                        sweepAngle = 180f,
                        useCenter = false,
                        style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = Color.White,
                        startAngle = 130f,
                        sweepAngle = 60f,
                        useCenter = false,
                        style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
                    )
                    drawArc(
                        color = Color.White,
                        startAngle = 210f,
                        sweepAngle = 60f,
                        useCenter = false,
                        style = Stroke(2.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                Canvas(modifier = Modifier.size(90.dp)) {
                    drawArc(
                        color = Color.White,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(0.2.dp.toPx(), cap = StrokeCap.Round)
                    )
                }

                Canvas(modifier = Modifier.size(130.dp)) {
                    drawArc(
                        color = Color.White,
                        startAngle = -70f,
                        sweepAngle = 80f,
                        useCenter = false,
                        style = Stroke(1f, cap = StrokeCap.Round)
                    )

                    drawLine(
                        color = Color.White,
                        start = Offset(size.width / 2 + 60f, -200f),
                        end = Offset(size.width / 2 + 60f, 10f),
                        strokeWidth = 1f
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(size.width + 4f, size.width / 2 + 40f),
                        end = Offset(size.width + 100f, size.width / 2 + 40f),
                        strokeWidth = 1f
                    )
                }

                Image(
                    painter = painterResource(R.drawable.homecodebarre),
                    contentDescription = "code barre",
                    modifier = Modifier.size(70.dp)
                )
            }

            Text(
                text = "SCAN PRODUIT",
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = dancingScript,
                modifier = Modifier.padding(top = 8.dp)
            )
        }


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


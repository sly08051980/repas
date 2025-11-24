package com.slyfly.repas.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.slyfly.repas.R


@Composable

fun HomeScreen() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxSize()){




                Canvas(modifier = Modifier.size(150.dp).align(Alignment.Center)) {
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


                Canvas(modifier = Modifier.size(130.dp).align(Alignment.Center)) {
                    drawArc(
                        color = Color.White,
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(0.2.dp.toPx(), cap = StrokeCap.Round)
                    )
                }


                Image(
                    painter = painterResource(R.drawable.homecodebarre),
                    contentDescription = "code barre",
                    modifier = Modifier.size(110.dp)
                )

            }
            Text(
                text = "SCAN PRODUIT 132",
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}



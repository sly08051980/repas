package com.slyfly.repas.ui.screen


import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.slyfly.repas.R
import com.slyfly.repas.core.barcode.BarCodeScanner
import com.slyfly.repas.logic.viewmodel.ScannerViewModel
import com.slyfly.repas.ui.nav.Routes
import com.slyfly.repas.ui.theme.dancingScript
import com.slyfly.repas.ui.theme.functionGradientBlueToWhite



@Composable
fun HomeScreen(
    viewModel: ScannerViewModel,
    navController:NavController
) {
    val context = LocalContext.current
    val state by viewModel.observeUiState().collectAsState()
    LaunchedEffect(state.navigateToDetail) {
        if (state.navigateToDetail) {
            navController.navigate(Routes.ScannerProductScreen)
            viewModel.resetNavigation()
        }
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

            Box(contentAlignment = Alignment.Center,modifier=Modifier

                ) {

                Canvas(modifier = Modifier.size(110.dp).clip(CircleShape).clickable { BarCodeScanner(context).startScanner { scannedCode ->
                    if (scannedCode != null) {
                        viewModel.scanProduct(scannedCode)

                    }
                } }) {
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
        Box(
         modifier = Modifier.fillMaxSize().padding(bottom = 350.dp, start = 40.dp), contentAlignment = Alignment.Center
        ){
            Image(painter= painterResource(R.drawable.hometicketdecaisse),
                contentDescription = "ticket de caisse",
                modifier=Modifier.size(120.dp)
                    .clickable(onClick = {/*TODO*/}))
        }



        HomeAnimateScreen()
    }
}
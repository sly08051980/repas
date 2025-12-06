package com.slyfly.repas.ui.screen

import android.graphics.drawable.Icon
import android.widget.ImageView
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.slyfly.repas.R
import com.slyfly.repas.domain.model.ScannerProduct
import com.slyfly.repas.logic.viewmodel.ScannerViewModel
import com.slyfly.repas.ui.theme.dancingScript
import org.koin.androidx.compose.koinViewModel




@Composable

fun ScannerProductScreen(
    viewModel:ScannerViewModel
){

    val state by viewModel.observeUiState().collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Column  {
                val nutriscore = state.nutrition_grade_fr
                val nutriscoreSize = Modifier.width(100.dp).height(70.dp).padding(top = 16.dp)
                Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement =   Arrangement.Center){

                        Text(state.product_name,
                            fontFamily = dancingScript,
                            fontSize = 50.sp,
                            color = Color.White,
                            style = TextStyle(lineHeight = 40.sp),
                            maxLines = 3,
                            textAlign = TextAlign.Center)

                }

                Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceAround ) {

                    AsyncImage(
                        model = state.image_front_url,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)

                    )
                    when (nutriscore) {
                        "a" -> Image(painter = painterResource(R.drawable.nutriscorea), null, nutriscoreSize)
                        "b" -> Image(painter = painterResource(R.drawable.nutriscoreb), null, nutriscoreSize)
                        "c" -> Image(painter = painterResource(R.drawable.nutriscorec), null, nutriscoreSize)
                        "d" -> Image(painter = painterResource(R.drawable.nutriscored), null, nutriscoreSize)
                        "e" -> Image(painter = painterResource(R.drawable.nutriscoree), null, nutriscoreSize)
                    }

                }
            }

            Spacer(Modifier.height(16.dp))
        }

        item {

            Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {

               // Text(state.brands)
                Text("${state.abbreviated_product_name_fr} ${state.product_quantity}${state.product_quantity_unit}",
                    fontFamily = dancingScript,
                    color = Color.White,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 40.sp))

                ScannerProductAnimateScreen()
                {expanded ->
                    Row( modifier = Modifier.fillMaxWidth()) {


                        Text(text = state.categories_old.takeIf { it.isNotBlank() }?.let { value ->
                                        buildAnnotatedString { withStyle(style = SpanStyle(fontFamily = dancingScript, fontSize = 20.sp)) { //pour mettre categorie dans une autre police
                                                append("Catégorie : ")
                                            }
                                            append(value)
                                        }
                                    } ?: buildAnnotatedString { append("") },
                            maxLines = if (expanded) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f),
                            color = Color.White
                        )
                        if (!expanded){
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "arrowDropDown",
                                modifier=Modifier.size(25.dp)
                                    . align(alignment = Alignment.CenterVertically)

                            )
                        }else{
                            Icon(
                                imageVector = Icons.Filled.ArrowDropUp,
                                contentDescription = "arrowDropUp",
                                modifier=Modifier.size(25.dp)
                                    .align(alignment = Alignment.Bottom)

                            )
                        }
                }
                }



                state.countries_imported.takeIf { it.isNotBlank() }?.let { value ->
                        Text(text = "Origine : $value", fontFamily = dancingScript, fontSize = 20.sp)
                    }
            }

            Spacer(Modifier.height(16.dp))
        }


        val data = listOfNotNull(
            state.allergens_from_user.takeIf { it.isNotBlank() }?.let {Triple("Allergène (déclaré): $it", Color.Red,false)  },
            state.allergens_from_ingredients.takeIf { it.isNotBlank() }?.let {Triple("Allergène (ingrédients): $it" , Color.Red,false)  },
            state.traces_from_ingredients.takeIf { it.isNotBlank() }?.let { Triple("Trace Allergène: $it" , Color.Red ,false) },
            state.ingredients_text_fr.takeIf { it.isNotBlank() }?.let { Triple("Ingrédients: $it", Color.White,true)  },
            state.preparation_fr.takeIf{it.isNotBlank()}?.let{Triple("Préparation : $it" , Color.White,true) },
            state.conservation_conditions_fr.takeIf{it.isNotBlank()}.let{Triple("Conservation : $it" , Color.White,true)}
        )

        items(data.size) { index ->
            val (text, color,expanded) = data[index]
            if (expanded){
                ScannerProductAnimateScreen { expanded->
                    Row (modifier=Modifier.fillMaxWidth()){
                        Text(text=text,
                            maxLines = if (expanded) Int.MAX_VALUE else 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(8.dp)
                                .weight(1f),
                            color = Color.White
                        )
                        if (!expanded){
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = "arrowDropDown",
                                modifier=Modifier.size(25.dp)
                                    . align(alignment = Alignment.CenterVertically)

                            )
                        }else{
                            Icon(
                                imageVector = Icons.Filled.ArrowDropUp,
                                contentDescription = "arrowDropUp",
                                modifier=Modifier.size(25.dp)
                                    .align(alignment = Alignment.Bottom)

                            )
                        }


                    }
                }

            }else{
                Text(text = text, color = color, modifier = Modifier.fillMaxWidth())
            }



        }

        item {
            Spacer(Modifier.height(16.dp))
            Text("Valeurs Nutritionnels pour 100g")
            Spacer(Modifier.height(8.dp))
        }


        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                val nut = listOf(
                    "Glucides : \n${state.nutriments?.carbohydrates_100g} g",
                    "Graisse : \n${state.nutriments?.fat_100g} g",
                    "Graisse saturée : \n${state.nutriments?.saturated_fat_100g} g",
                    "Sucres : \n${state.nutriments?.sugars_100g} g",
                    "Sel : \n${state.nutriments?.salt_100g} g",
                    "Sodium : \n${state.nutriments?.sodium_100g} g",
                    "Fibres : \n${state.nutriments?.fiber_100g} g",
                    "Protéines : \n${state.nutriments?.proteins_100g} g",
                    "Énergie : \n${state.nutriments?.energy_kcal_100g} kcal",
                    "Énergie : \n${state.nutriments?.energy_kj_100g} kJ"
                )

                items(nut) { nutr ->
                    OutlinedCard(modifier=Modifier.height(80.dp).aspectRatio(1f),colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFD4D2D2)),border = BorderStroke(1.dp, Color.Black),) {Text(nutr,textAlign = TextAlign.Center,modifier=Modifier.fillMaxSize()) }


                }
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun grettingScan(){

}
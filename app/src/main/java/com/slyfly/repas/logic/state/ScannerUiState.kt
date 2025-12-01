package com.slyfly.repas.logic.state

import com.google.gson.annotations.SerializedName

data class ScannerUiState(
    val navigateToDetail: Boolean = false,
    val error :String?=null,
    val isLoading:Boolean=false,
    val success:Boolean=false,
    val product_name:String="",
    val brands: String="",
    val abbreviated_product_name_fr :String="",
    val allergens_from_ingredients : String="",
    val allergens_from_user :String="",
    val categories_old:String="",
    val conservation_conditions_fr:String="",
    val countries_imported:String="",
    val image_front_url:String="",
    val ingredients_text_fr:String="",
    val nutrition_grade_fr:String="",
    val nutriments: ScannerProductNutrimentsUiState?=ScannerProductNutrimentsUiState(),
    val preparation_fr:String="",
    val product_quantity:Double=0.0,
    val product_quantity_unit:String="",
    val _id:String="",
    val traces_from_ingredients:String=""
)
data class ScannerProductNutrimentsUiState(
    val carbohydrates_100g:Double=0.0,
 val energy_kcal_100g:Double=0.0,
val energy_kj_100g:Double=0.0,
    val fat_100g:Double=0.0,
    val fiber_100g:Double=0.0,
    val proteins_100g:Double=0.0,
    val salt_100g:Double=0.0,
 val saturated_fat_100g:Double=0.0,
    val sodium_100g:Double=0.0,
    val sugars_100g:Double=0.0
)



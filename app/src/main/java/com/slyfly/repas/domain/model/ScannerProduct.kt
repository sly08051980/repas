package com.slyfly.repas.domain.model

import com.google.gson.annotations.SerializedName


data class ScannerProduct (
    val product_name: String?,
    val brands: String?,
    val abbreviated_product_name_fr :String?,
    val allergens_from_ingredients : String?,
    val allergens_from_user :String?,
    val categories_old:String?,
    val conservation_conditions_fr:String?,
    val countries_imported:String?,
    val image_front_url:String?,
    val ingredients_text_fr:String?,
    val nutrition_grade_fr:String?,
    val nutriments: ScannerProductNutriments?,
    val preparation_fr:String?,
    val product_quantity:Double?,
    val product_quantity_unit:String?,
    val _id:Long?,
    val traces_from_ingredients:String?
)
data class ScannerProductNutriments(
    val carbohydrates_100g:Double?,
    @SerializedName("energy-kcal_100g") val energy_kcal_100g:Double?,
    @SerializedName("energy-kj_100g") val energy_kj_100g:Double?,
    val fat_100g:Double?,
    val fiber_100g:Double?,
    val proteins_100g:Double?,
    val salt_100g:Double?,
    @SerializedName("saturated-fat_100g") val saturated_fat_100g:Double?,
    val sodium_100g:Double?,
    val sugars_100g:Double?
)

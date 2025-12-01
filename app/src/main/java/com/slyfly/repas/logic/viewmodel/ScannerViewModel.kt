package com.slyfly.repas.logic.viewmodel


import android.content.Context
import android.content.IntentSender.OnFinished
import androidx.lifecycle.viewModelScope
import com.slyfly.repas.core.base.BaseViewModel
import com.slyfly.repas.domain.usecase.ScannerUseCase
import com.slyfly.repas.logic.state.ScannerProductNutrimentsUiState
import com.slyfly.repas.logic.state.ScannerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.slyfly.repas.core.validation.allergenSorted
class ScannerViewModel(
    private val scannerUseCase: ScannerUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ScannerUiState())
    fun observeUiState(): StateFlow<ScannerUiState> = _uiState

    fun scanProduct(barCode: String) {
        viewModelScope.launch {
            val result = scannerUseCase(barCode)

            setLoading(true)


            _uiState.value = _uiState.value.copy(
                error = null,
                success = false
            )
            try {

                if (result.isSuccess) {

                    val product = result.getOrNull()
                    println("Produit scanné : ${product?.product_name}")
                    println("Marque : ${product?.brands}")
                    println("Nutrition grade : ${product?.nutrition_grade_fr}")
                } else {
                    println("Erreur scan : ${result.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                println("Exception: ${e.message}")
            }



            result.onSuccess { product ->

           val allergensIngredients= allergenSorted(product.allergens_from_ingredients?:"")
val allergensUsers= allergenSorted(product.allergens_from_user ?: "")

                _uiState.value = ScannerUiState(
                    navigateToDetail = true,
                    isLoading = false,
                    success = true,
                    product_name = product.product_name ?: "",
                    brands = product.brands ?: "",
                    abbreviated_product_name_fr = product.abbreviated_product_name_fr ?: "",
                   // allergens_from_ingredients = product.allergens_from_ingredients ?: "",
                    allergens_from_ingredients = allergensIngredients,
                    //allergens_from_user = product.allergens_from_user ?: "",

                   allergens_from_user =allergensUsers,
                    traces_from_ingredients=product.traces_from_ingredients?:"",
                    categories_old = product.categories_old ?: "",
                    conservation_conditions_fr = product.conservation_conditions_fr ?: "",
                    countries_imported = product.countries_imported ?: "",
                    image_front_url = product.image_front_url ?: "",
                    ingredients_text_fr = product.ingredients_text_fr ?: "",
                    nutrition_grade_fr = product.nutrition_grade_fr ?: "",
                    preparation_fr = product.preparation_fr ?: "",
                    product_quantity = product.product_quantity ?: 0.0,
                    product_quantity_unit = product.product_quantity_unit ?: "",
                    _id = (product._id ?: 0).toString(),
                    nutriments = ScannerProductNutrimentsUiState(
                        carbohydrates_100g = product.nutriments?.carbohydrates_100g ?: 0.0,
                        energy_kcal_100g = product.nutriments?.energy_kcal_100g ?: 0.0,
                        energy_kj_100g = product.nutriments?.energy_kj_100g ?: 0.0,
                        fat_100g = product.nutriments?.fat_100g ?: 0.0,
                        fiber_100g = product.nutriments?.fiber_100g ?: 0.0,
                        proteins_100g = product.nutriments?.proteins_100g ?: 0.0,
                        salt_100g = product.nutriments?.salt_100g ?: 0.0,
                        saturated_fat_100g = product.nutriments?.saturated_fat_100g ?: 0.0,
                        sodium_100g = product.nutriments?.sodium_100g ?: 0.0,
                        sugars_100g = product.nutriments?.sugars_100g ?: 0.0
                    )
                )
            }

            result.onFailure { e ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.localizedMessage ?: "Erreur inconnue",
                    success = false
                )
            }


            setLoading(false)
        }
    }
    fun resetNavigation() {
        _uiState.value = _uiState.value.copy(navigateToDetail = false)
    }
}

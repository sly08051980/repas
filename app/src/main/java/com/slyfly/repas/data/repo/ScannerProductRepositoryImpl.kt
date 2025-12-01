package com.slyfly.repas.data.repo

import com.slyfly.repas.data.service.ScannerService
import com.slyfly.repas.domain.model.ScannerProduct
import com.slyfly.repas.domain.model.ScannerProductNutriments
import com.slyfly.repas.domain.repository.ScannerProductRepository

class ScannerProductRepositoryImpl(
    private val api: ScannerService
) : ScannerProductRepository {

    override suspend fun scannerProduct(barCode: String): Result<ScannerProduct> {
        return try {

            val res = api.scannerProduct(barCode)

            if (res.status != 1 || res.product == null) {
                return Result.failure(Exception("Produit introuvable"))
            }

            val p = res.product

            Result.success(
                ScannerProduct(
                    product_name = p.product_name,
                    brands = p.brands,
                    abbreviated_product_name_fr = p.abbreviated_product_name_fr,
                    allergens_from_ingredients = p.allergens_from_ingredients,
                    allergens_from_user = p.allergens_from_user,
                    categories_old = p.categories_old,
                    conservation_conditions_fr = p.conservation_conditions_fr,
                    countries_imported = p.countries_imported,
                    image_front_url = p.image_front_url,
                    ingredients_text_fr = p.ingredients_text_fr,
                    nutrition_grade_fr = p.nutrition_grade_fr,
                    traces_from_ingredients=p.traces_from_ingredients,
                    nutriments = p.nutriments?.let {
                        ScannerProductNutriments(
                            carbohydrates_100g = it.carbohydrates_100g,
                            energy_kcal_100g = it.energy_kcal_100g,
                            energy_kj_100g = it.energy_kj_100g,
                            fat_100g = it.fat_100g,
                            fiber_100g = it.fiber_100g,
                            proteins_100g = it.proteins_100g,
                            salt_100g = it.salt_100g,
                            saturated_fat_100g = it.saturated_fat_100g,
                            sodium_100g = it.sodium_100g,
                            sugars_100g = it.sugars_100g
                        )
                    },
                    preparation_fr = p.preparation_fr,
                    product_quantity = p.product_quantity,
                    product_quantity_unit = p.product_quantity_unit,
                    _id = p._id
                )
            )

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

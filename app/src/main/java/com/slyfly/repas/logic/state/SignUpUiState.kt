package com.slyfly.repas.logic.state

data class SignUpUiState(
    val firstName: String = "",
    val lastName: String = "",
    val city:String="",
    val postalCode:String="",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

package com.slyfly.repas.ui.components

data class SignInField(
    val label: String,
    val value: String,
    val onChange: (String) -> Unit,
    val isPassword: Boolean = false
) {



}
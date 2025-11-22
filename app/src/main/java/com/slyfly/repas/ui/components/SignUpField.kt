package com.slyfly.repas.ui.components

data class SignUpField(
    val label: String,
    val value: String,
    val onChange: (String) -> Unit,
    val isPassword: Boolean = false
)

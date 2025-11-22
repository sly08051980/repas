package com.slyfly.repas.logic.interfaces

sealed class SignUpIntent {
    data class ChangeFirstName(val value: String) : SignUpIntent()
    data class ChangeLastName(val value: String) : SignUpIntent()
    data class ChangeCity(val value: String):SignUpIntent()
    data class ChangePostalCode(val value: String):SignUpIntent()
    data class ChangeEmail(val value: String) : SignUpIntent()
    data class ChangePassword(val value: String) : SignUpIntent()
    data class ChangeConfirmPassword(val value: String) : SignUpIntent()
    object Submit : SignUpIntent()
    object ClearError : SignUpIntent()
}

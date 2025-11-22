package com.slyfly.repas.logic.interfaces

sealed class SignInItent{
    data class ChangeEmail(val value:String):SignInItent()
    data class ChangePassword(val value: String):SignInItent()
    object Submit:SignInItent()
    object ClearError:SignInItent()
}
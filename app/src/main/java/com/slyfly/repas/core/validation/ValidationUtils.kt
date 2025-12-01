package com.slyfly.repas.core.validation

import android.util.Patterns


fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()
}


fun isValidPassword(password: String): Boolean {
    val passwordRegex =
        Regex("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$")
    return passwordRegex.matches(password)
}


fun isValidNameField(input: String): Boolean {
    if (input.isBlank()) return false


    val forbidden = listOf(";", "--", "/*", "*/", "'", "\"", "DROP", "SELECT", "INSERT", "UPDATE", "DELETE")
    if (forbidden.any { input.uppercase().contains(it) }) return false


    val regex = Regex("^[A-Za-zÀ-ÿ' -]{2,}$")
    return regex.matches(input)
}


fun isValidPostalCode(code: String): Boolean {
    val regex = Regex("^[0-9]{5}$")
    return regex.matches(code)
}

fun allergenSorted(allergenCleaned: String): String {
    return allergenCleaned
        .replace(Regex("en:[^,;]*([,;]|$)"), "")
        .replace("(fr)","")
        .split(",", ";")
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .toSet()
        .joinToString(", ")
}

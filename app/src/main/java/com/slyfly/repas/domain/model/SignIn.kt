package com.slyfly.repas.domain.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class SignIn(
    val email: String,
    val token: String
)

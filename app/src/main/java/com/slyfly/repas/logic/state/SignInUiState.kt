package com.slyfly.repas.logic.state

import android.provider.ContactsContract.CommonDataKinds.Email

data class SignInUiState(
    val email:String="",
    val password:String="",
    val isLoading: Boolean=false,
    val error: String? = null,
    val success:Boolean=false

)

package com.slyfly.repas.logic.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.slyfly.repas.core.base.BaseViewModel
import com.slyfly.repas.core.validation.isValidEmail
import com.slyfly.repas.domain.usecase.SignInUserUseCase
import com.slyfly.repas.logic.interfaces.SignInItent
import com.slyfly.repas.logic.state.SignInUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel( private val signInUserUseCase:SignInUserUseCase) :BaseViewModel(){
    private val _uiState= MutableStateFlow(SignInUiState())
    fun observeUiState():StateFlow<SignInUiState> = _uiState

    fun onIntent(intent:SignInItent){
        when(intent){
            is SignInItent.ChangeEmail->
                _uiState.value = _uiState.value.copy(email = intent.value, error = null)
            is SignInItent.ChangePassword->
                _uiState.value = _uiState.value.copy(password = intent.value, error  =null)

            SignInItent.Submit -> submit()
            SignInItent.ClearError->
                _uiState.value = _uiState.value.copy(error = null)
        }
    }
    private fun submit(){
        val s = _uiState.value
        if (s.email.isBlank()||s.password.isBlank()){
            _uiState.value=s.copy(error = "Tous les champs ne sont pas rempli")
            return
        }
        if (!isValidEmail(s.email)) {
            _uiState.value = s.copy(error = "Adresse email invalide")
            return
        }
        _uiState.value=s.copy(isLoading = true,error=null)
        setLoading(true)
        //pour dire si valider allez sur home qui est ds le screen
        _uiState.value = s.copy(isLoading = false, success = true)
        viewModelScope.launch {
            val result=signInUserUseCase(s.email.trim().lowercase(),s.password)
            _uiState.value = result.fold(
                onSuccess = {
                    _uiState.value.copy(
                        isLoading = false,
                        success = true
                    )

                },
                onFailure = {
                    _uiState.value.copy(
                        isLoading = false,
                        error = it.message ?: "Erreur inconnue"
                    )
                }
            )
            setLoading(false)
        }
    }
}
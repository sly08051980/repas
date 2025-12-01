package com.slyfly.repas.logic.viewmodel

import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.viewModelScope
import com.slyfly.repas.core.base.BaseViewModel
import com.slyfly.repas.core.validation.isValidEmail
import com.slyfly.repas.core.validation.isValidNameField
import com.slyfly.repas.core.validation.isValidPassword
import com.slyfly.repas.core.validation.isValidPostalCode
import com.slyfly.repas.logic.interfaces.SignUpIntent
import com.slyfly.repas.logic.state.SignUpUiState
import com.slyfly.repas.domain.usecase.RegisterUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val registerUserUseCase: RegisterUserUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    fun observeUiState(): StateFlow<SignUpUiState> = _uiState

    fun onIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.ChangeFirstName ->
                _uiState.value = _uiState.value.copy(firstName = intent.value, error = null)
            is SignUpIntent.ChangeLastName ->
                _uiState.value = _uiState.value.copy(lastName = intent.value, error = null)
            is SignUpIntent.ChangeCity->
                _uiState.value=  _uiState.value.copy(city = intent.value,error=null)
            is SignUpIntent.ChangePostalCode->
                _uiState.value=  _uiState.value.copy(postalCode = intent.value, error = null)
            is SignUpIntent.ChangeEmail ->
                _uiState.value = _uiState.value.copy(email = intent.value, error = null)
            is SignUpIntent.ChangePassword ->
                _uiState.value = _uiState.value.copy(password = intent.value, error = null)
            is SignUpIntent.ChangeConfirmPassword ->
                _uiState.value = _uiState.value.copy(confirmPassword = intent.value, error = null)
            SignUpIntent.Submit -> submit()
            SignUpIntent.ClearError ->
                _uiState.value = _uiState.value.copy(error = null)
        }
    }


    private fun submit() {
        val s = _uiState.value
        if (s.firstName.isBlank() || s.lastName.isBlank() || s.email.isBlank() || s.password.isBlank()) {
            _uiState.value = s.copy(error = "Tous les champs sont obligatoires")
            return
        }
        if (s.password != s.confirmPassword) {
            _uiState.value = s.copy(error = "Les mots de passe ne correspondent pas")
            return
        }
        if (!isValidPassword(s.password)) {
            _uiState.value = s.copy(error = "Le mot de passe doit contenir :\n- 1 majuscule\n- 1 minuscule\n- 1 chiffre\n- 1 caractère spécial\n- Min 8 caractères")
            return
        }
        if (!isValidNameField(s.firstName)) {
            _uiState.value = s.copy(error = "Prénom invalide")
            return
        }

        if (!isValidNameField(s.lastName)) {
            _uiState.value = s.copy(error = "Nom invalide")
            return
        }

        if (!isValidNameField(s.city)) {
            _uiState.value = s.copy(error = "Ville invalide")
            return
        }

        if (!isValidPostalCode(s.postalCode)) {
            _uiState.value = s.copy(error = "Code postal invalide")
            return
        }

        if (!isValidEmail(s.email)) {
            _uiState.value = s.copy(error = "Adresse email invalide")
            return
        }


        _uiState.value = s.copy(isLoading = true, error = null)
        setLoading(true)

        viewModelScope.launch {
            val result = registerUserUseCase(
                firstName = s.firstName.trim().lowercase(),
                lastName = s.lastName.trim().lowercase(),
                city = s.city.trim().lowercase(),
                postalCode = s.postalCode.trim(),
                email = s.email.trim().lowercase(),
                password = s.password
            )
            _uiState.value = result.fold(
                onSuccess = { s.copy(isLoading = false, success = true) },
                onFailure = { s.copy(isLoading = false, error = it.message) }
            )
            setLoading(false)
        }
    }
}

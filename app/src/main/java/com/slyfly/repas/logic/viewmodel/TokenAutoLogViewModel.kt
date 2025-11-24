package com.slyfly.repas.logic.viewmodel

import androidx.lifecycle.viewModelScope
import com.slyfly.repas.core.base.BaseViewModel
import com.slyfly.repas.core.datastore.SessionManager
import com.slyfly.repas.domain.usecase.TokenUserUseCase
import com.slyfly.repas.logic.state.TokenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TokenAutoLogViewModel(
    private val tokenUserUseCase: TokenUserUseCase,
    private val session: SessionManager
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(TokenUiState())
    val uiState: StateFlow<TokenUiState> = _uiState

    fun checkToken(token: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _uiState.value = TokenUiState(isLoading = true)

            val result = tokenUserUseCase(token)

            _uiState.value = result.fold(
                onSuccess = {
                    TokenUiState(
                        success = true,
                        token = it.token,
                        user = it.user
                    )
                },
                onFailure = {
                    TokenUiState(
                        error = it.message ?: "Erreur inconnue"
                    )
                }
            )

            onResult(_uiState.value.success)
        }
    }

}

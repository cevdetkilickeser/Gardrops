package com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.gardrops.data.repository.AuthRepository
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInWithUsernameOrEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.EmailOrUsernameChanged -> updateUiState { copy(emailOrUsername = action.emailOrUsername) }
            is UiAction.PasswordChanged -> updateUiState { copy(password = action.password) }
            is UiAction.PasswordVisibilityChanged -> updateUiState { copy(isPasswordVisible = !uiState.value.isPasswordVisible) }
            is UiAction.SignInButtonClicked -> signInWithEmailOrUsername(action.emailOrUsername, action.password)
            UiAction.RememberPasswordClicked -> viewModelScope.launch { emitUiEffect(UiEffect.NavigateToForgotPasswordScreen) }
        }
    }

    private fun signInWithEmailOrUsername(emailOrUsername: String, password: String) {
        viewModelScope.launch {
            val authResult = authRepository.signInWithEmailOrUsername(emailOrUsername, password)
            if (authResult.first) {
                emitUiEffect(UiEffect.NavigateToHomeScreen)
            } else {
                emitUiEffect(UiEffect.ShowSnackbar(authResult.second))
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
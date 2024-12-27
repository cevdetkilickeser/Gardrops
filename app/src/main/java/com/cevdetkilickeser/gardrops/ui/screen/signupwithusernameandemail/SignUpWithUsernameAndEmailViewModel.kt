package com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.gardrops.data.repository.AuthRepository
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiState
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
class SignUpWithUsernameAndEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.UsernameChanged -> updateUiState { copy(username = action.username) }
            is UiAction.EmailChanged -> updateUiState { copy(email = action.email) }
            is UiAction.PasswordChanged -> updateUiState { copy(password = action.password) }
            UiAction.PasswordVisibilityChanged -> updateUiState { copy(isPasswordVisible = !uiState.value.isPasswordVisible) }
            UiAction.UserAgreementCheckboxClicked -> updateUiState { copy(isUserAgreementChecked = !uiState.value.isUserAgreementChecked) }
            UiAction.UserAgreementTextClicked -> viewModelScope.launch { emitUiEffect(UiEffect.NavigateToUserAgreementScreen) }
            UiAction.CommercialEmailCheckboxClicked -> updateUiState { copy(isCommercialEmailChecked = !uiState.value.isCommercialEmailChecked) }
            UiAction.CommercialEmailTextClicked -> viewModelScope.launch { emitUiEffect(UiEffect.NavigateToCommercialEmailScreen) }
            UiAction.PrivacyPolicyTextClicked -> viewModelScope.launch { emitUiEffect(UiEffect.NavigateToPrivacyPolicyScreen) }
            is UiAction.SignUpButtonClicked -> signUpClicked(
                action.username,
                action.email,
                action.password,
                action.isUserAgreementChecked
            )
        }
    }

    private fun signUpClicked(
        username: String,
        email: String,
        password: String,
        isUserAgreementChecked: Boolean
    ) {
        viewModelScope.launch {
            val result = authRepository.signUpWithUsernameOrEmail(
                username,
                email,
                password,
                isUserAgreementChecked
            )
            if (result.first) {
                emitUiEffect(UiEffect.NavigateToHomeScreen)
            } else {
                emitUiEffect(UiEffect.ShowSnackbar(result.second))
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
package com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiEffect
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
class SignUpWithUsernameOrEmailViewModel @Inject constructor(): ViewModel() {

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
            is UiAction.SignUpButtonClicked -> TODO()
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
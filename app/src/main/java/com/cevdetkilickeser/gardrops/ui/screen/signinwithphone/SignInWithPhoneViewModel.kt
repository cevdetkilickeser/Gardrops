package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiEffect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(): ViewModel(){

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.PhoneNumberChanged -> phoneNumberChanged(action.text)
            UiAction.ClearPhoneNumberClicked -> phoneNumberChanged("0 (5")
            UiAction.SignInClicked -> TODO()
            UiAction.SignInWithUsernameOrEmailClicked -> viewModelScope.launch {
                emitUiEffect(UiEffect.NavigateToSignInWithUsernameOrEmailScreen)
            }
        }
    }

    private fun phoneNumberChanged(text: String) {
        when (text.length) {
            4 -> {
                updateUiState { copy(phoneNumber = text, isClearTextIconVisible = false, isSignInButtonEnabled = false) }
            }
            6 -> {
                val editedText = "$text) "
                updateUiState { copy(phoneNumber = editedText, isSignInButtonEnabled = false) }
            }
            11 -> {
                val editedText = "$text "
                updateUiState { copy(phoneNumber = editedText, isSignInButtonEnabled = false) }
            }
            14 -> {
                val editedText = "$text "
                updateUiState { copy(phoneNumber = editedText, isSignInButtonEnabled = false) }
            }
            17 -> {
                updateUiState { copy(phoneNumber = text, isSignInButtonEnabled = true) }
            }
            else -> {
                updateUiState { copy(phoneNumber = text, isClearTextIconVisible = true, isSignInButtonEnabled = false) }
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
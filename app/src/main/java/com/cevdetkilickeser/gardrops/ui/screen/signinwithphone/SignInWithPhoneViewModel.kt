package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cevdetkilickeser.gardrops.navigation.Screen
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType
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
class SignInWithPhoneViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val args = savedStateHandle.toRoute<Screen.SignInWithPhone>()
        updateUiState { copy(continueType = args.continueType) }
    }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.PhoneNumberChanged -> phoneNumberChanged(action.text)
            UiAction.ClearPhoneNumberClicked -> phoneNumberChanged("0 (5")
            UiAction.SignInClicked -> TODO()
            is UiAction.ContinueWithUsernameOrEmailClicked -> continueWithUsernameOrEmailClicked(action.continueType)
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

    private fun continueWithUsernameOrEmailClicked(continueType: ContinueType) {
        viewModelScope.launch {
            if (continueType == ContinueType.SIGN_IN) {
                emitUiEffect(UiEffect.NavigateToSignInWithUsernameOrEmailScreen)
            } else {
                emitUiEffect(UiEffect.NavigateToSignUpWithUsernameOrEmailScreen)
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
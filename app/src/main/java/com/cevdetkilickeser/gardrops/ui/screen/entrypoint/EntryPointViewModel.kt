package com.cevdetkilickeser.gardrops.ui.screen.entrypoint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType

@HiltViewModel
class EntryPointViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: UiAction) {
        when (action) {

            UiAction.SignUpClicked -> updateUiState { copy(isSignUpBottomSheetVisible = true) }
            UiAction.SignInClicked -> updateUiState { copy(isSignInBottomSheetVisible = true) }
            is UiAction.ContinueWithFacebookClicked -> { }
            is UiAction.ContinueWithPhoneClicked -> viewModelScope.launch {
                emitUiEffect(UiEffect.NavigateToSignInWithPhoneScreen(action.continueType))
            }
            UiAction.SignInWithUsernameOrEmailTextClicked -> viewModelScope.launch {
                emitUiEffect(UiEffect.NavigateToSignInWithUsernameOrEmailScreen)
            }
            UiAction.UserAgreementClicked -> viewModelScope.launch {
                emitUiEffect(UiEffect.NavigateToUserAgreementScreen)
            }
            UiAction.PrivacyPolicyClicked -> viewModelScope.launch {
                emitUiEffect(UiEffect.NavigateToPrivacyPolicyScreen)
            }
            UiAction.SignUpBottomSheetDismissed -> updateUiState { copy(isSignUpBottomSheetVisible = false) }
            UiAction.SignInBottomSheetDismissed -> updateUiState { copy(isSignInBottomSheetVisible = false) }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
package com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms.VerifySMSContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms.VerifySMSContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms.VerifySMSContract.UiAction
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
class VerifySMSViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.SmsCodeChanged -> smsCodeChanged(action.smsCode)
        }
    }

    private fun smsCodeChanged(smsCode: String) {
        updateUiState { copy(smsCode = smsCode) }
        if (smsCode.length == 6) {
            viewModelScope.launch { emitUiEffect(UiEffect.NavigateToHomeScreen) }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
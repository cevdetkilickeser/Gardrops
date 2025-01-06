package com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms

object VerifySMSContract {
    data class UiState(
        val smsCode: String = ""
    )

    sealed interface UiAction {
        data class SmsCodeChanged(val smsCode: String) : UiAction
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data object NavigateToHomeScreen : UiEffect()
    }
}
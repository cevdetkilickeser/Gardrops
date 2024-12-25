package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

object SignInWithPhoneContract {
    data class UiState(
        val phoneNumber: String = "0 (5",
        val isClearTextIconVisible: Boolean = false,
        val isSignInButtonEnabled: Boolean = false
    )

    sealed interface UiAction {
        data class PhoneNumberChanged(val text: String) : UiAction
        data object ClearPhoneNumberClicked : UiAction
        data object SignInClicked : UiAction
        data object SignInWithUsernameOrEmailClicked : UiAction
    }

    sealed class UiEffect {
        data object NavigateToSignInWithUsernameOrEmailScreen : UiEffect()
        data object NavigateToHomeScreen : UiEffect()
    }
}
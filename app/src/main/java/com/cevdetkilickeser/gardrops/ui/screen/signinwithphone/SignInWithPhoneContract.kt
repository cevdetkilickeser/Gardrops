package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType

object SignInWithPhoneContract {
    data class UiState(
        val continueType: ContinueType = ContinueType.SIGN_IN,
        val phoneNumber: String = "0 (5",
        val isClearTextIconVisible: Boolean = false,
        val isSignInButtonEnabled: Boolean = false
    )

    sealed interface UiAction {
        data class PhoneNumberChanged(val phoneNumber: String) : UiAction
        data object ClearPhoneNumberClicked : UiAction
        data object SignInClicked : UiAction
        data class ContinueWithUsernameOrEmailClicked(val continueType: ContinueType) : UiAction
    }

    sealed class UiEffect {
        data object NavigateToSignInWithUsernameOrEmailScreen : UiEffect()
        data object NavigateToSignUpWithUsernameOrEmailScreen : UiEffect()
        data object NavigateToHomeScreen : UiEffect()
    }
}
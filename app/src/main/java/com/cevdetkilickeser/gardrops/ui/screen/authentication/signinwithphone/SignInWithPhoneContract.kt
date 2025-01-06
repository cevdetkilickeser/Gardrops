package com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithphone

import com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.composable.ContinueType

object SignInWithPhoneContract {
    data class UiState(
        val continueType: ContinueType = ContinueType.SIGN_IN,
        val phoneNumber: String = "0 (5",
        val isClearTextIconVisible: Boolean = false,
        val isSignInButtonEnabled: Boolean = true
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
        data object NavigateToVerifySMSScreen : UiEffect()
    }
}
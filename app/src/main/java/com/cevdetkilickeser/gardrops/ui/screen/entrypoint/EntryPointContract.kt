package com.cevdetkilickeser.gardrops.ui.screen.entrypoint

import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType

object EntryPointContract {
    data class UiState(
        val isSignUpBottomSheetVisible: Boolean = false,
        val isSignInBottomSheetVisible: Boolean = false
    )

    sealed interface UiAction {
        data object SignUpClicked : UiAction
        data object SignInClicked : UiAction
        data class ContinueWithFacebookClicked(val continueType: ContinueType) : UiAction
        data class ContinueWithPhoneClicked(val continueType: ContinueType)  : UiAction
        data object SignInWithUsernameOrEmailTextClicked : UiAction
        data object UserAgreementClicked : UiAction
        data object PrivacyPolicyClicked : UiAction
        data object SignUpBottomSheetDismissed: UiAction
        data object SignInBottomSheetDismissed: UiAction
    }

    sealed class UiEffect {
        data class NavigateToSignInWithPhoneScreen(val continueType: ContinueType) : UiEffect()
        data object NavigateToSignInWithUsernameOrEmailScreen : UiEffect()
        data object NavigateToUserAgreementScreen : UiEffect()
        data object NavigateToPrivacyPolicyScreen : UiEffect()
    }
}
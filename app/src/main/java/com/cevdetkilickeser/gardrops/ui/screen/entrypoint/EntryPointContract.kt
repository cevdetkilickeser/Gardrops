package com.cevdetkilickeser.gardrops.ui.screen.entrypoint

object EntryPointContract {
    data class UiState(
        val isSignUpBottomSheetVisible: Boolean = false,
        val isSignInBottomSheetVisible: Boolean = false
    )

    sealed interface UiAction {
        data object SignUpClicked : UiAction
        data object SignInClicked : UiAction
        data object SignUpWithFacebookClicked : UiAction
        data object SignInWithFacebookClicked : UiAction
        data object SignInWithPhoneClicked : UiAction
        data object SignInWithUsernameOrEmailTextClicked : UiAction
        data object UserAgreementClicked : UiAction
        data object PrivacyPolicyClicked : UiAction
        data object SignUpBottomSheetDismissed: UiAction
        data object SignInBottomSheetDismissed: UiAction
    }

    sealed class UiEffect {
        data object NavigateToSignInWithPhoneScreen : UiEffect()
        data object NavigateToSignInWithUsernameOrEmailScreen : UiEffect()
        data object NavigateToUserAgreementScreen : UiEffect()
        data object NavigateToPrivacyPolicyScreen : UiEffect()
    }
}
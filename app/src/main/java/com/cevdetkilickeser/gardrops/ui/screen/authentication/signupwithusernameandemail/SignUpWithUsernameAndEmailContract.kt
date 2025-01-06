package com.cevdetkilickeser.gardrops.ui.screen.authentication.signupwithusernameandemail

object SignUpWithUsernameAndEmailContract {
    data class UiState(
        val username: String = "",
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val isUserAgreementChecked: Boolean = false,
        val isCommercialEmailChecked: Boolean = false
    )

    sealed interface UiAction {
        data class UsernameChanged(val username: String) : UiAction
        data class EmailChanged(val email: String) : UiAction
        data class PasswordChanged(val password: String) : UiAction
        data object PasswordVisibilityChanged : UiAction
        data object UserAgreementCheckboxClicked : UiAction
        data object UserAgreementTextClicked : UiAction
        data object CommercialEmailCheckboxClicked : UiAction
        data object CommercialEmailTextClicked : UiAction
        data object PrivacyPolicyTextClicked : UiAction
        data class SignUpButtonClicked(
            val username: String,
            val email: String,
            val password: String,
            val isUserAgreementChecked: Boolean
        ) : UiAction

    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data object NavigateToHomeScreen : UiEffect()
        data object NavigateToUserAgreementScreen : UiEffect()
        data object NavigateToCommercialEmailScreen : UiEffect()
        data object NavigateToPrivacyPolicyScreen : UiEffect()
    }
}
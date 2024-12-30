package com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail

object SignInWithUsernameOrEmailContract {

    data class UiState(
        val emailOrUsername: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false
    )

    sealed interface UiAction {
        data class EmailOrUsernameChanged(val emailOrUsername: String) : UiAction
        data class PasswordChanged(val password: String) : UiAction
        data object PasswordVisibilityChanged : UiAction
        data class SignInButtonClicked(val emailOrUsername: String, val password: String) : UiAction
        data object RememberPasswordClicked : UiAction
    }

    sealed class UiEffect {
        data class ShowSnackbar(val message: String) : UiEffect()
        data object NavigateToHomeScreen : UiEffect()
        data object NavigateToForgotPasswordScreen : UiEffect()

    }
}
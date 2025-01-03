package com.cevdetkilickeser.gardrops.navigation

import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object EntryPoint : Screen

    @Serializable
    data class SignInWithPhone(val continueType: ContinueType) : Screen

    @Serializable
    data object VerifySMS : Screen

    @Serializable
    data object SignInWithUsernameOrEmail : Screen

    @Serializable
    data object SignUpWithUsernameOrEmail : Screen

    @Serializable
    data object UserAgreement : Screen

    @Serializable
    data object PrivacyPolicy : Screen

    @Serializable
    data object CommercialEmail : Screen

    @Serializable
    data object RememberPassword : Screen

    @Serializable
    data object Home : Screen
}
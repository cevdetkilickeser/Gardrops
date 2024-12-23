package com.cevdetkilickeser.gardrops.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object EntryPoint : Screen

    @Serializable
    data object SignInWithPhone : Screen

    @Serializable
    data object SignInWithUsernameOrEmail : Screen

    @Serializable
    data object UserAgreement : Screen

    @Serializable
    data object PrivacyPolicy : Screen

    @Serializable
    data object Home : Screen
}
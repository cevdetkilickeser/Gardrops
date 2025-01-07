package com.cevdetkilickeser.gardrops.navigation

import com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.composable.ContinueType
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

    @Serializable
    data object Search : Screen

    @Serializable
    data object Add : Screen

    @Serializable
    data object Notification : Screen

    @Serializable
    data object Profile : Screen
}

val bottomBarScreens = listOf(
    Screen.Home,
    Screen.Search,
    Screen.Notification,
    Screen.Profile
)

fun String.toScreen(): Screen? {
    val screen = this.split(".").last()
    return when (screen) {
        Screen.Home.toString() -> Screen.Home
        Screen.Search.toString() -> Screen.Search
        Screen.Notification.toString() -> Screen.Notification
        Screen.Profile.toString() -> Screen.Profile
        else -> null
    }
}
package com.cevdetkilickeser.gardrops.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cevdetkilickeser.gardrops.mobileNumberFilter
import com.cevdetkilickeser.gardrops.ui.screen.add.AddScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.commercialemail.CommercialEmailScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.EntryPointScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.EntryPointViewModel
import com.cevdetkilickeser.gardrops.ui.screen.home.HomeScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.privacypolicy.PrivacyPolicyScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms.VerifySMSViewModel
import com.cevdetkilickeser.gardrops.ui.screen.authentication.rememberpassword.RememberPasswordScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithphone.SignInWithPhoneScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithphone.SignInWithPhoneViewModel
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail.SignInWithUsernameOrEmailScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signinwithusernameoremail.SignInWithUsernameOrEmailViewModel
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signupwithusernameandemail.SignUpWithUsernameAndEmail
import com.cevdetkilickeser.gardrops.ui.screen.authentication.signupwithusernameandemail.SignUpWithUsernameAndEmailViewModel
import com.cevdetkilickeser.gardrops.ui.screen.authentication.useragreement.UserAgreementScreen
import com.cevdetkilickeser.gardrops.ui.screen.authentication.verifysms.VerifySMSScreen
import com.cevdetkilickeser.gardrops.ui.screen.notification.NotificationScreen
import com.cevdetkilickeser.gardrops.ui.screen.profile.ProfileScreen
import com.cevdetkilickeser.gardrops.ui.screen.search.SearchScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<Screen.EntryPoint>(
            exitTransition = Transitions.exitTransition,
            popEnterTransition = Transitions.popEnterTransition
        ) {
            val viewModel = hiltViewModel<EntryPointViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            EntryPointScreen(
                uiState = uiState.value,
                uiEffect = uiEffect,
                uiAction = viewModel::onAction,
                navigateToSignInWithPhoneScreen = { continueType ->
                    navController.navigate(Screen.SignInWithPhone(continueType))
                },
                navigateToSignInWithUsernameOrEmailScreen = { navController.navigate(Screen.SignInWithUsernameOrEmail) },
                navigateToUserAgreementScreen = { navController.navigate(Screen.UserAgreement) },
                navigateToPrivacyPolicyScreen = { navController.navigate(Screen.PrivacyPolicy) }
            )
        }
        composable<Screen.SignInWithPhone>(
            enterTransition = Transitions.enterTransition,
            exitTransition = Transitions.exitTransition,
            popEnterTransition = Transitions.popEnterTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            val viewModel = hiltViewModel<SignInWithPhoneViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignInWithPhoneScreen(
                uiState = uiState.value,
                uiEffect = uiEffect,
                uiAction = viewModel::onAction,
                mobileNumberFilter = { annotatedString ->
                    mobileNumberFilter(annotatedString)
                },
                navigateToSignInWithUsernameOrEmail = { navController.navigate(Screen.SignInWithUsernameOrEmail) },
                navigateToSignUpWithUsernameOrEmail = { navController.navigate(Screen.SignUpWithUsernameOrEmail) },
                navigateToVerifySMSScreen = { navController.navigate(Screen.VerifySMS) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable<Screen.VerifySMS>(
            enterTransition = Transitions.enterTransition,
            exitTransition = Transitions.exitTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            val viewModel = hiltViewModel<VerifySMSViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            VerifySMSScreen(
                uiState = uiState.value,
                uiEffect = uiEffect,
                uiAction = viewModel::onAction,
                navigateToHomeScreen = { navController.navigate(Screen.Home) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable<Screen.SignUpWithUsernameOrEmail>(
            enterTransition = Transitions.enterTransition,
            exitTransition = Transitions.exitTransition,
            popExitTransition = Transitions.popExitTransition
        )  {
            val viewModel = hiltViewModel<SignUpWithUsernameAndEmailViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignUpWithUsernameAndEmail(
                uiState = uiState.value,
                uiEffect = uiEffect,
                uiAction = viewModel::onAction,
                navigateToHomeScreen = { navController.navigate(Screen.Home) },
                navigateToUserAgreementScreen = { navController.navigate(Screen.UserAgreement) },
                navigateToCommercialEmailScreen = { navController.navigate(Screen.CommercialEmail) },
                navigateToPrivacyPolicyScreen = { navController.navigate(Screen.PrivacyPolicy) },
                onBackClick = { navController.popBackStack() },
            )
        }
        composable<Screen.SignInWithUsernameOrEmail>(
            enterTransition = Transitions.enterTransition,
            exitTransition = Transitions.exitTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            val viewModel = hiltViewModel<SignInWithUsernameOrEmailViewModel>()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignInWithUsernameOrEmailScreen(
                uiState = uiState.value,
                uiEffect = uiEffect,
                uiAction = viewModel::onAction,
                navigateToHomeScreen = { navController.navigate(Screen.Home) },
                navigateToRememberPasswordScreen = { navController.navigate(Screen.RememberPassword) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable<Screen.RememberPassword>(
            enterTransition = Transitions.enterTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            RememberPasswordScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.UserAgreement>(
            enterTransition = Transitions.enterTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            UserAgreementScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.PrivacyPolicy>(
            enterTransition = Transitions.enterTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            PrivacyPolicyScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.CommercialEmail>(
            enterTransition = Transitions.enterTransition,
            popExitTransition = Transitions.popExitTransition
        ) {
            CommercialEmailScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.Home> {
            HomeScreen()
        }
        composable<Screen.Search> {
            SearchScreen()
        }
        composable<Screen.Add>(
            enterTransition = Transitions.enterTransition,
            popExitTransition = Transitions.popExitTransition) {
            AddScreen()
        }
        composable<Screen.Notification> {
            NotificationScreen()
        }
        composable<Screen.Profile> {
            ProfileScreen()
        }
    }
}
package com.cevdetkilickeser.gardrops.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cevdetkilickeser.gardrops.mobileNumberFilter
import com.cevdetkilickeser.gardrops.ui.screen.commercialemail.CommercialEmailScreen
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointScreen
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointViewModel
import com.cevdetkilickeser.gardrops.ui.screen.home.HomeScreen
import com.cevdetkilickeser.gardrops.ui.screen.privacypolicy.PrivacyPolicyScreen
import com.cevdetkilickeser.gardrops.ui.screen.verifysms.VerifySMSViewModel
import com.cevdetkilickeser.gardrops.ui.screen.rememberpassword.RememberPasswordScreen
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneScreen
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneViewModel
import com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail.SignInWithUsernameOrEmailScreen
import com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail.SignInWithUsernameOrEmailViewModel
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmail
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailViewModel
import com.cevdetkilickeser.gardrops.ui.screen.useragreement.UserAgreementScreen
import com.cevdetkilickeser.gardrops.ui.screen.verifysms.VerifySMSScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.EntryPoint> {
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
        composable<Screen.SignInWithPhone> {
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
        composable<Screen.VerifySMS> {
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
        composable<Screen.SignUpWithUsernameOrEmail> {
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
        composable<Screen.SignInWithUsernameOrEmail> {
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
        composable<Screen.RememberPassword> {
            RememberPasswordScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.UserAgreement> {
            UserAgreementScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.PrivacyPolicy> {
            PrivacyPolicyScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.CommercialEmail> {
            CommercialEmailScreen(
                onCloseClick = { navController.popBackStack() }
            )
        }
        composable<Screen.Home> {
            HomeScreen()
        }
    }
}
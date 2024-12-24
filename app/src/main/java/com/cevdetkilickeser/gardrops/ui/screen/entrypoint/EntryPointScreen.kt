package com.cevdetkilickeser.gardrops.ui.screen.entrypoint

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.R
import com.cevdetkilickeser.gardrops.common.CollectWithLifecycle
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.EntryPointContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.AuthBottomSheet
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.CustomOutlinedButton
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.GradientButton
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.SignInWithUsernameOrEmailText
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.AgreementAndPrivacyText
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun EntryPointScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    uiAction: (UiAction) -> Unit,
    navigateToSignInWithPhone: () -> Unit,
    navigateToSignInWithUsernameOrEmail: () -> Unit,
    navigateToUserAgreement: () -> Unit,
    navigateToPrivacyPolicy: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val padding = screenHeight / 10

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            UiEffect.NavigateToSignInWithPhoneScreen -> navigateToSignInWithPhone()
            UiEffect.NavigateToSignInWithUsernameOrEmailScreen -> navigateToSignInWithUsernameOrEmail()
            UiEffect.NavigateToUserAgreementScreen -> navigateToUserAgreement()
            UiEffect.NavigateToPrivacyPolicyScreen -> navigateToPrivacyPolicy()
        }
    }

    if (uiState.isSignUpBottomSheetVisible) {
        AuthBottomSheet(
            title = "Gardrops'a Katıl",
            facebookClick = { uiAction(UiAction.SignUpWithFacebookClicked) },
            phoneClick = { uiAction(UiAction.SignInWithPhoneClicked) },
            bottomText = {
                AgreementAndPrivacyText(
                    onTermsClicked = { uiAction(UiAction.UserAgreementClicked) },
                    onPrivacyClicked = { uiAction(UiAction.PrivacyPolicyClicked) }
                )
            },
            onDismissRequest = { uiAction(UiAction.SignUpBottomSheetDismissed) }
        )
    }

    if (uiState.isSignInBottomSheetVisible) {
        AuthBottomSheet(
            title = "Giriş Yap",
            facebookClick = { uiAction(UiAction.SignUpWithFacebookClicked) },
            phoneClick = { uiAction(UiAction.SignInWithPhoneClicked) },
            bottomText = {
                SignInWithUsernameOrEmailText (
                    onClick = { uiAction(UiAction.SignInWithUsernameOrEmailTextClicked) }
                )
            },
            onDismissRequest = { uiAction(UiAction.SignInBottomSheetDismissed) }
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.jpg_entry_point),
            contentDescription = null,
            modifier = Modifier.padding(top = padding)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = padding),
        ) {
            GradientButton(
                text = "Gardrops'a Katıl",
                onClick = { uiAction(UiAction.SignUpClicked) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomOutlinedButton(
                text = "Zaten üye misin? Giriş yap",
                onClick = { uiAction(UiAction.SignInClicked) }
            )
        }
    }
}

@Preview
@Composable
private fun EntryPointScreenPreview() {
    GardropsTheme {
        EntryPointScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            uiAction = {},
            navigateToSignInWithPhone = {},
            navigateToSignInWithUsernameOrEmail = {},
            navigateToUserAgreement = {},
            navigateToPrivacyPolicy = {}
        )
    }
}
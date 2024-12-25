package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.common.CollectWithLifecycle
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.GradientButton
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.SignInWithUsernameOrEmailText
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignInWithPhoneScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    uiAction: (UiAction) -> Unit,
    navigateToSignInWithUsernameOrEmail: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    onBackClick: () -> Unit
) {

    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            UiEffect.NavigateToSignInWithUsernameOrEmailScreen -> navigateToSignInWithUsernameOrEmail()
            UiEffect.NavigateToHomeScreen -> navigateToHomeScreen()
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = { onBackClick() },
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
                        contentDescription = null
                    )
                }
                Text(
                    text = "Giriş Yap",
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Cep Telefonu")
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    BasicTextField(
                        value = uiState.phoneNumber,
                        onValueChange = { uiAction(UiAction.PhoneNumberChanged(it)) },
                        textStyle = MaterialTheme.typography.bodyLarge,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    )
                    if (uiState.isClearTextIconVisible) {
                        IconButton(
                            onClick = { uiAction(UiAction.ClearPhoneNumberClicked) },
                            modifier = Modifier.size(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .imePadding()
        ) {
            GradientButton(
                text = "Giriş Yap",
                onClick = { uiAction(UiAction.SignInClicked) },
                enabled = uiState.isSignInButtonEnabled
            )
            Spacer(modifier = Modifier.height(16.dp))
            SignInWithUsernameOrEmailText(
                onClick = { uiAction(UiAction.SignInWithUsernameOrEmailClicked) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun SignInWithPhoneScreenPreview() {
    GardropsTheme {
        SignInWithPhoneScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            uiAction = {},
            navigateToSignInWithUsernameOrEmail = {},
            navigateToHomeScreen = {},
            onBackClick = {}
        )
    }
}
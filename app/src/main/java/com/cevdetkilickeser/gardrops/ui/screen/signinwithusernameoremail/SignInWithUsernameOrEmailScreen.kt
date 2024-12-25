package com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.common.CollectWithLifecycle
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.GradientButton
import com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.signinwithusernameoremail.SignInWithUsernameOrEmailContract.UiState
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignInWithUsernameOrEmailScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    uiAction: (UiAction) -> Unit,
    navigateToHomeScreen: () -> Unit,
    navigateToForgotPassword: () -> Unit,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    uiEffect.CollectWithLifecycle { action ->
        when (action) {
            UiEffect.NavigateToHomeScreen -> navigateToHomeScreen()
            UiEffect.NavigateToForgotPasswordScreen -> navigateToForgotPassword()
            is UiEffect.ShowToast -> { Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show() }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
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
        OutlinedTextField(
            value = uiState.emailOrUsername,
            onValueChange = { uiAction(UiAction.EmailOrUsernameChanged(it)) },
            placeholder = { Text(text = "E-posta adresi veya kullanıcı adı") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray
            )
        )
        HorizontalDivider(
            thickness = 0.5.dp,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            color = Color.LightGray
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { uiAction(UiAction.PasswordChanged(it)) },
            placeholder = { Text(text = "Şifre") },
            visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(
                    onClick = { uiAction(UiAction.PasswordVisibilityChanged) },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .padding(end = 32.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = if (uiState.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedPlaceholderColor = Color.LightGray,
                unfocusedPlaceholderColor = Color.LightGray
            ),
            modifier = Modifier.fillMaxWidth()
        )
        HorizontalDivider(
            thickness = 0.5.dp,
            modifier = Modifier
                .padding(horizontal = 16.dp),
            color = Color.LightGray
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            GradientButton(
                text = "Giriş Yap",
                onClick = { uiAction(UiAction.SignInButtonClicked(uiState.emailOrUsername, uiState.password)) }
            )
            Text(
                text = "Şifremi Unuttum",
                color = Color.LightGray,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable { uiAction(UiAction.ForgotPasswordClicked) }
            )
        }
    }
}

@Preview
@Composable
private fun SignInWithUsernameOrEmailScreenPreview() {
    GardropsTheme {
        SignInWithUsernameOrEmailScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            uiAction = {},
            navigateToHomeScreen = {},
            navigateToForgotPassword = {},
            onBackClick = {}
        )
    }
}
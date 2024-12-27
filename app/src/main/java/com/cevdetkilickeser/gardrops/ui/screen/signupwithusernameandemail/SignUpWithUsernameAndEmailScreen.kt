package com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.R
import com.cevdetkilickeser.gardrops.common.CollectWithLifecycle
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.GradientButton
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail.SignUpWithUsernameAndEmailContract.UiState
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignUpWithUsernameAndEmail(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    uiAction: (UiAction) -> Unit,
    navigateToHomeScreen: () -> Unit,
    navigateToUserAgreementScreen: () -> Unit,
    navigateToCommercialEmailScreen: () -> Unit,
    navigateToPrivacyPolicyScreen: () -> Unit,
    onBackClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    var message by remember { mutableStateOf<String?>(null) }
    var snackbarTrigger by remember { mutableIntStateOf(0) }

    uiEffect.CollectWithLifecycle { action ->
        when (action) {
            UiEffect.NavigateToCommercialEmailScreen -> navigateToCommercialEmailScreen()
            UiEffect.NavigateToHomeScreen -> navigateToHomeScreen()
            UiEffect.NavigateToPrivacyPolicyScreen -> navigateToPrivacyPolicyScreen()
            UiEffect.NavigateToUserAgreementScreen -> navigateToUserAgreementScreen()
            is UiEffect.ShowSnackbar -> {
                message = action.message
                snackbarTrigger++
            }
        }
    }

    LaunchedEffect(snackbarTrigger) {
        message?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState,Modifier.imePadding()) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            ) {
                IconButton(
                    onClick = { onBackClick() }, modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBackIos, contentDescription = null
                    )
                }
                Text(
                    text = "Üye Ol", modifier = Modifier.align(Alignment.Center)
                )
            }
            OutlinedTextField(
                value = uiState.username,
                onValueChange = { uiAction(UiAction.UsernameChanged(it)) },
                placeholder = { Text(text = "Kullanıcı adı") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray
                )
            )
            HorizontalDivider(
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.LightGray
            )
            OutlinedTextField(
                value = uiState.email,
                onValueChange = { uiAction(UiAction.EmailChanged(it)) },
                placeholder = { Text(text = "E-posta adresi") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    focusedPlaceholderColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray
                )
            )
            HorizontalDivider(
                thickness = 0.5.dp,
                modifier = Modifier.padding(horizontal = 16.dp),
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
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.LightGray
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(3.dp),
                        modifier = Modifier.size(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        IconButton(onClick = { uiAction(UiAction.UserAgreementCheckboxClicked) }) {
                            if (uiState.isUserAgreementChecked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.png_checked),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    AcceptUserAgreementText(onUserAgreementClicked = { uiAction(UiAction.UserAgreementTextClicked) })
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(3.dp),
                        modifier = Modifier.size(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        IconButton(onClick = { uiAction(UiAction.CommercialEmailCheckboxClicked) }) {
                            if (uiState.isCommercialEmailChecked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.png_checked),
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    AcceptCommercialEmailText(
                        onCommercialEmailClicked = { uiAction(UiAction.CommercialEmailTextClicked) },
                        onPrivacyPolicyClicked = { uiAction(UiAction.PrivacyPolicyTextClicked) }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                GradientButton(
                    text = "Üye Ol", onClick = {
                        uiAction(
                            UiAction.SignUpButtonClicked(
                                uiState.username,
                                uiState.email,
                                uiState.password,
                                uiState.isUserAgreementChecked
                            )
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpWithUsernameAndEmailPreview() {
    GardropsTheme {
        SignUpWithUsernameAndEmail(uiState = UiState(),
            uiEffect = emptyFlow(),
            uiAction = {},
            navigateToHomeScreen = { },
            navigateToUserAgreementScreen = { },
            navigateToCommercialEmailScreen = { },
            navigateToPrivacyPolicyScreen = { },
            onBackClick = {})
    }
}
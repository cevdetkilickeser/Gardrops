package com.cevdetkilickeser.gardrops.ui.screen.verifysms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cevdetkilickeser.gardrops.common.CollectWithLifecycle
import com.cevdetkilickeser.gardrops.ui.screen.verifysms.VerifySMSContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.verifysms.VerifySMSContract.UiEffect
import com.cevdetkilickeser.gardrops.ui.screen.verifysms.VerifySMSContract.UiState
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun VerifySMSScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    uiAction: (UiAction) -> Unit,
    onBackClick: () -> Unit,
    navigateToHomeScreen: () -> Unit
) {
    uiEffect.CollectWithLifecycle { effect ->
        when (effect) {
            UiEffect.NavigateToHomeScreen -> navigateToHomeScreen()
            is UiEffect.ShowSnackbar -> { }
        }
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column {
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
            }
            Text(
                text = "Telefonunuza gönderilen 6 haneli kodu giriniz.",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp)
            )
            TextField(
                value = uiState.smsCode,
                onValueChange = { uiAction(UiAction.SmsCodeChanged(it)) },
                textStyle = MaterialTheme.typography.titleLarge.copy(
                    letterSpacing = 12.sp,
                    fontSize = 36.sp,
                    lineHeight = 36.sp
                ),
                placeholder = {
                    Text(
                        text = "000000",
                        fontSize = 36.sp,
                        color = Color.LightGray,
                        style = MaterialTheme.typography.titleLarge.copy(
                            letterSpacing = 12.sp
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(color = Color.Transparent)
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .imePadding()
        ) {
            Text("Şifreniz 1 dakika içinde SMS olarak gelecektir.")
            Spacer(Modifier.height(16.dp))
            Text("Kodu Tekrar Gönder")
        }
    }
}

@Preview
@Composable
private fun VerifySMSScreenPreview() {
    GardropsTheme {
        VerifySMSScreen(
            uiState = UiState(),
            uiEffect = emptyFlow(),
            uiAction = {},
            onBackClick = {},
            navigateToHomeScreen = {}
        )
    }
}
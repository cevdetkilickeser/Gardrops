package com.cevdetkilickeser.gardrops.ui.screen.authentication.useragreement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cevdetkilickeser.gardrops.ui.screen.authentication.useragreement.composable.WebPageViewer
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun UserAgreementScreen(
    onCloseClick: () -> Unit,
) {
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
                onClick = { onCloseClick() },
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
            Text(
                text = "Kullanıcı Sözleşmesi",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        WebPageViewer(url = "https://gardrops.com/pages/kullanici-sozlesmesi")
    }
}

@Preview
@Composable
private fun UserAgreementScreenPreview() {
    GardropsTheme {
        UserAgreementScreen(
            onCloseClick = {}
        )
    }
}
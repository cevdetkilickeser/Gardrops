package com.cevdetkilickeser.gardrops.ui.screen.rememberpassword

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.R
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun RememberPasswordScreen(
    onCloseClick: () -> Unit
) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
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
                text = "Şifremi Hatırlat",
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Image(
            painter = painterResource(
                id = R.drawable.jpg_remember_password
            ),
            contentDescription = null
        )
        HorizontalDivider()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { Toast.makeText(context, "Bu ekrandaki çalışma devam etmektedir.", Toast.LENGTH_SHORT).show() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_id_card),
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = "Kullanıcı adımla şifremi sıfırla",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(horizontal = 64.dp, vertical = 16.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            )
        }
        HorizontalDivider()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { Toast.makeText(context, "Bu ekrandaki çalışma devam etmektedir.", Toast.LENGTH_SHORT).show() }
        ) {
            Icon(
                imageVector = Icons.Outlined.Email,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .size(36.dp)
            )
            Text(
                text = "E-posta adresimle şifremi sıfırla",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(horizontal = 64.dp, vertical = 16.dp)
            )
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
            )
        }
        HorizontalDivider()
    }
}

@Preview(showBackground = true)
@Composable
private fun RememberPasswordScreenPreview() {
    GardropsTheme {
        RememberPasswordScreen(
            onCloseClick = {}
        )
    }
}
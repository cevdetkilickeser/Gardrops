package com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun GradientButton(
    text: String,
    onClick: () -> Unit,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                if (enabled) onClick()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = Brush.horizontalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                ))
        ) {
            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                )
            }
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.Center)
            )

        }
    }
//    Button(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                brush = Brush.horizontalGradient(
//                    colors = listOf(
//                        MaterialTheme.colorScheme.primary,
//                        MaterialTheme.colorScheme.secondary
//                    )
//                ),
//                shape = RoundedCornerShape(8.dp),
//            ),
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Transparent,
//        ),
//        enabled = enabled,
//        contentPadding = PaddingValues(),
//        onClick = { onClick() },
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(color = Color.Transparent)
//        ) {
//            icon?.let {
//                Icon(
//                    imageVector = it,
//                    contentDescription = null,
//                    tint = MaterialTheme.colorScheme.onPrimary,
//                    modifier = Modifier
//                        .align(Alignment.CenterStart)
//                        .padding(vertical = 4.dp, horizontal = 16.dp),
//                )
//            }
//            Text(
//                text = text,
//                color = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier
//                    .align(Alignment.Center)
//            )
//        }
//    }
}

@Preview
@Composable
private fun GradientButtonPreview() {
    GardropsTheme {
        GradientButton(
            text = "Telefon ile Giriş yap",
            onClick = { },
            icon = Icons.Default.Phone,
            enabled = false
        )
    }
}
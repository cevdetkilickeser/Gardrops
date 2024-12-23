package com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun SignInWithUsernameOrEmailText(
    onClick: () -> Unit
) {
    val text = buildAnnotatedString {
        append("Kullanıcı adı")
        addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = 0, end = 13)

        append(" ya da ")

        append("email")
        addStyle(style = SpanStyle(fontWeight = FontWeight.Bold), start = 20, end = 25)

        append(" ile devam et")
    }

    BasicText(
        text = text,
        style = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier.clickable { onClick() }
    )
}

@Preview
@Composable
private fun SignInTextPreview() {
    GardropsTheme {
        SignInWithUsernameOrEmailText {}
    }
}
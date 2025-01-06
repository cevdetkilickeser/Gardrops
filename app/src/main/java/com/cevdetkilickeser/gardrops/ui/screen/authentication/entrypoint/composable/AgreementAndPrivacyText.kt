package com.cevdetkilickeser.gardrops.ui.screen.authentication.entrypoint.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.cevdetkilickeser.gardrops.ui.theme.GardropsTheme

@Composable
fun AgreementAndPrivacyText(
    onUserAgreementClicked: () -> Unit,
    onPrivacyPolicyClicked: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        append("Devam ederek ")

        pushStringAnnotation(tag = "AGREEMENT", annotation = "agreement")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Kullanıcı Sözleşmesini")
        }
        pop()

        append(" ve ")

        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Gizlilik Politikasını")
        }
        pop()

        append(" kabul ediyorum.")
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        ),
        onClick = { offset ->
            annotatedText.getStringAnnotations(start = offset, end = offset).firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        "AGREEMENT" -> onUserAgreementClicked()
                        "PRIVACY" -> onPrivacyPolicyClicked()
                    }
                }
        }
    )
}

@Preview
@Composable
private fun AgreementAndPrivacyTextPreview() {
    GardropsTheme {
        AgreementAndPrivacyText(
            onUserAgreementClicked = {},
            onPrivacyPolicyClicked = {}
        )
    }
}

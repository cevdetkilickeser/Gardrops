package com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
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
    onTermsClicked: () -> Unit,
    onPrivacyClicked: () -> Unit
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

    Text(
        text = annotatedText,
        style = TextStyle(fontSize = 12.sp, textAlign = TextAlign.Center),
        color = MaterialTheme.colorScheme.onBackground,
        onTextLayout = { layoutResult ->
            val clickHandler: (Int) -> Unit = { offset ->
                annotatedText.getStringAnnotations(start = offset, end = offset).firstOrNull()
                    ?.let { annotation ->
                        when (annotation.tag) {
                            "AGREEMENT" -> onTermsClicked()
                            "PRIVACY" -> onPrivacyClicked()
                        }
                    }
            }
            Modifier.pointerInput(Unit) {
                detectTapGestures { position ->
                    val offset = layoutResult.getOffsetForPosition(position)
                    clickHandler(offset)
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
            onTermsClicked = {},
            onPrivacyClicked = {}
        )
    }
}

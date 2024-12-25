package com.cevdetkilickeser.gardrops.ui.screen.signupwithusernameandemail

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
fun AcceptUserAgreementText(
    onUserAgreementClicked: () -> Unit
) {
    val annotatedText = buildAnnotatedString {

        pushStringAnnotation(tag = "AGREEMENT", annotation = "agreement")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("Kullanıcı Sözleşmesi")
        }
        pop()

        append(" hükümlerini kabul ediyorum.")
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        ),
        onClick = { offset ->
            annotatedText.getStringAnnotations(start = offset, end = offset).firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        "AGREEMENT" -> onUserAgreementClicked()
                    }
                }
        }
    )
}

@Preview
@Composable
private fun AcceptUserAgreementTextPreview() {
    GardropsTheme {
        AcceptUserAgreementText(
            onUserAgreementClicked = {}
        )
    }
}

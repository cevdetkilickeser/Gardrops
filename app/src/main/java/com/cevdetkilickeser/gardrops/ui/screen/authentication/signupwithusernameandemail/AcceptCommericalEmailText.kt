package com.cevdetkilickeser.gardrops.ui.screen.authentication.signupwithusernameandemail

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
fun AcceptCommercialEmailText(
    onCommercialEmailClicked: () -> Unit,
    onPrivacyPolicyClicked: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        append("Tarafıma ")

        pushStringAnnotation(tag = "COMMERCIAL", annotation = "commercial")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("ticari elektronik ileti")
        }
        pop()

        append(" gönderilmesini ve kişisel verilerimin ")

        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy")
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append("aydnlatma metninde")
        }
        pop()

        append(" belirtilen amaçlarla işlenmesini ve yurt içi/yurt dışında barındırılmasını kabul ediyorum.")
    }

    ClickableText(
        text = annotatedText,
        style = TextStyle(
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onBackground
        ),
        onClick = { offset ->
            annotatedText.getStringAnnotations(start = offset, end = offset).firstOrNull()
                ?.let { annotation ->
                    when (annotation.tag) {
                        "COMMERCIAL" -> onCommercialEmailClicked()
                        "PRIVACY" -> onPrivacyPolicyClicked()
                    }
                }
        }
    )
}

@Preview
@Composable
private fun AcceptCommercialEmailTextPreview() {
    GardropsTheme {
        AcceptCommercialEmailText(
            onCommercialEmailClicked = {},
            onPrivacyPolicyClicked = {}
        )
    }
}

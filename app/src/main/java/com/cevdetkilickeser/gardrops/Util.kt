package com.cevdetkilickeser.gardrops

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

val regex = "^[a-zA-Z0-9_]{3,}$".toRegex()

fun String.toPhone(): String {
    val digits = extractDigits(this)
    val phoneNumber = mutableListOf<String>()
    when (digits.size) {
        1 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
        }

        2 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
        }

        3 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
        }

        4 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
        }

        5 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
        }

        6 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
        }

        7 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
            phoneNumber.add(10, digits[6])
            phoneNumber.add(11, " ")
        }

        8 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
            phoneNumber.add(10, digits[6])
            phoneNumber.add(11, " ")
            phoneNumber.add(12, digits[7])
        }

        9 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
            phoneNumber.add(10, digits[6])
            phoneNumber.add(11, " ")
            phoneNumber.add(12, digits[7])
            phoneNumber.add(13, digits[8])
            phoneNumber.add(14, " ")
        }

        10 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
            phoneNumber.add(10, digits[6])
            phoneNumber.add(11, " ")
            phoneNumber.add(12, digits[7])
            phoneNumber.add(13, digits[8])
            phoneNumber.add(14, " ")
            phoneNumber.add(15, digits[9])
        }

        11 -> {
            phoneNumber.clear()
            phoneNumber.add(0, "0")
            phoneNumber.add(1, " ")
            phoneNumber.add(2, "(")
            phoneNumber.add(3, "5")
            phoneNumber.add(4, digits[2])
            phoneNumber.add(5, digits[3])
            phoneNumber.add(6, ")")
            phoneNumber.add(7, " ")
            phoneNumber.add(8, digits[4])
            phoneNumber.add(9, digits[5])
            phoneNumber.add(10, digits[6])
            phoneNumber.add(11, " ")
            phoneNumber.add(12, digits[7])
            phoneNumber.add(13, digits[8])
            phoneNumber.add(14, " ")
            phoneNumber.add(15, digits[9])
            phoneNumber.add(16, digits[10])
        }
    }
    return phoneNumber.joinToString("")
}

private fun extractDigits(text: String): MutableList<String> {
    return text.filter { it.isDigit() }
        .map { it.toString() }.toMutableList()
}

fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    val formattedPhone = text.text.toPhone()
    val origToTransformedOffset = formattedPhone.length
    val formattedLength = formattedPhone.filterNot {
        it.isDigit()
    }.count()

    val annotatedString = AnnotatedString(formattedPhone)

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return origToTransformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when (offset) {
                in 0..5 -> 2
                in 6..10 -> 4
                in 11..13 -> 5
                in 14..16 -> 6
                else -> 6
            }
        }
    }
    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}
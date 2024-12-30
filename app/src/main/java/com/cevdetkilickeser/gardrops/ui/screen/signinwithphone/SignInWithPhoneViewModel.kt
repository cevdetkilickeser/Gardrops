package com.cevdetkilickeser.gardrops.ui.screen.signinwithphone

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.cevdetkilickeser.gardrops.navigation.Screen
import com.cevdetkilickeser.gardrops.toPhone
import com.cevdetkilickeser.gardrops.ui.screen.entrypoint.composable.ContinueType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiState
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiAction
import com.cevdetkilickeser.gardrops.ui.screen.signinwithphone.SignInWithPhoneContract.UiEffect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class SignInWithPhoneViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect by lazy { Channel<UiEffect>() }
    val uiEffect: Flow<UiEffect> by lazy { _uiEffect.receiveAsFlow() }

    init {
        val args = savedStateHandle.toRoute<Screen.SignInWithPhone>()
        updateUiState { copy(continueType = args.continueType) }
    }

    fun onAction(action: UiAction) {
        when (action) {
            is UiAction.PhoneNumberChanged -> if (action.phoneNumber.length < 14) updateUiState { copy(phoneNumber = action.phoneNumber) }
            UiAction.ClearPhoneNumberClicked -> updateUiState { copy(phoneNumber = "0 (5") }
            UiAction.SignInClicked -> TODO()
            is UiAction.ContinueWithUsernameOrEmailClicked -> continueWithUsernameOrEmailClicked(action.continueType)
        }
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
                Timber.d("transformedOffset: $offset")
                return if (offset > 5) offset - formattedLength else offset
            }
        }
        return TransformedText(annotatedString, phoneNumberOffsetTranslator)
    }

    private fun continueWithUsernameOrEmailClicked(continueType: ContinueType) {
        viewModelScope.launch {
            if (continueType == ContinueType.SIGN_IN) {
                emitUiEffect(UiEffect.NavigateToSignInWithUsernameOrEmailScreen)
            } else {
                emitUiEffect(UiEffect.NavigateToSignUpWithUsernameOrEmailScreen)
            }
        }
    }

    private fun updateUiState(block: UiState.() -> UiState) {
        _uiState.update(block)
    }

    private suspend fun emitUiEffect(uiEffect: UiEffect) {
        _uiEffect.send(uiEffect)
    }
}
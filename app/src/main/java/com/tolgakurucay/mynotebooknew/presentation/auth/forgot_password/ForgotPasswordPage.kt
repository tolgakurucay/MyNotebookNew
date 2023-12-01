package com.tolgakurucay.mynotebooknew.presentation.auth.forgot_password

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing10
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing30
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing40
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing70

@Composable
fun ForgotPasswordPage(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit = {}
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    if(state.sendResetMail){
        CustomAlertDialog(
            type = AlertDialogType.OKAY, titleRes = R.string.common_information,
            descriptionRes = stringResource(
                id = R.string.screen_forgotpassword_successful
            ),
            onConfirm = { onNavigateToLogin.invoke() }
        )
    }



    Surface(Modifier.fillMaxSize()) {
        ForgotPasswordContent(
            onNavigateToLogin,
            state = state,
            forgotPassword = {
                viewModel.forgotPassword(it)
            },
        )
    }
}

@Preview
@Composable
fun ForgotPasswordContent(
    onNavigateToLogin: () -> Unit = {},
    state: ForgotPasswordState = ForgotPasswordState(),
    forgotPassword: (email: String) -> Unit = {}
) {
    var email by remember { mutableStateOf<String?>(null) }

    BaseColumn(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        state = state
    ) {

        Text(
            text = stringResource(id = R.string.common_forgot_password_uppercase),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = spacing30, top = spacing30, start = spacing30),
            style = MaterialTheme.typography.displaySmall
        )

        Spacer(modifier = Modifier.padding(vertical = spacing70))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.EMAIL,
            onValueChange = {
                email = it
            },
        )
        Spacer(modifier = Modifier.padding(top = spacing40))

        CustomButton(
            buttonType = ButtonType.FORGOT_PASSWORD,
            horizontalMargin = spacing10,
            onClick = {
                if (arrayOf(email).validateCustomTextFields()) {
                    forgotPassword.invoke(email!!)
                }
            },
        )
        Spacer(modifier = Modifier.padding(top = spacing40))

    }


}
package com.tolgakurucay.mynotebooknew.presentation.auth.register

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
import com.tolgakurucay.mynotebooknew.domain.base.arePasswordsSame
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.model.auth.CreateUserEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.marginExtraLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.marginSmall
import com.tolgakurucay.mynotebooknew.presentation.theme.pageDividerMedium
import com.tolgakurucay.mynotebooknew.util.safeLet

@Composable
fun Register(
    onNavigateToLogin: () -> Unit = {},
    registerViewModel: RegisterViewModel = hiltViewModel()
) {

    val state by registerViewModel.state.collectAsStateWithLifecycle()

    Surface(modifier = Modifier.fillMaxSize()) {
        RegisterContent(
            onNavigateToLoginChild = onNavigateToLogin,
            uiState = state,
            register = { request ->
                registerViewModel.registerUser(request)
            }
        )
    }
}


@Preview
@Composable
fun RegisterContent(
    onNavigateToLoginChild: () -> Unit = {},
    uiState: RegisterState = RegisterState(),
    register: (model: CreateUserEmailPasswordRequest) -> Unit = {}
) {

    var email by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }
    var passwordAgain by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf<String?>(null) }
    var surname by remember { mutableStateOf<String?>(null) }

    var isShowEmptyFieldsAlert by remember { mutableStateOf(false) }
    var isShowPasswordAlert by remember { mutableStateOf(false) }


    fun register() {
        if (arrayOf(
                email,
                password,
                passwordAgain,
                name,
                surname
            )
                .validateCustomTextFields()
        ) {
            if (arePasswordsSame(password = password, passwordAgain = passwordAgain)) {
                //Viewmodel processes
                safeLet(name, surname, email, password, "") { name, sname, email, password, phone ->
                    register.invoke(
                        CreateUserEmailPasswordRequest(
                            email,
                            password,
                            name,
                            sname,
                            phone
                        )
                    )
                }

            } else {
                isShowPasswordAlert = true
            }

        } else {
            isShowEmptyFieldsAlert = true
        }
    }

    @Composable
    fun observeData() {
        if (isShowEmptyFieldsAlert) {
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                titleRes = R.string.common_information,
                descriptionText = stringResource(id = R.string.action_fill_empty_fields_correctly),
                onConfirm = {
                    isShowEmptyFieldsAlert = false
                },
            )
        } else if (isShowPasswordAlert) {
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                titleRes = R.string.common_information,
                descriptionText = stringResource(id = R.string.common_passwords_not_same),
                onConfirm = {
                    isShowPasswordAlert = false
                },
            )
        }

        if (uiState.isUserRegistered == true) {
            uiState.isUserRegistered = false
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                titleRes = R.string.common_information,
                descriptionText = stringResource(
                    id = R.string.screen_register_successful
                ), onConfirm = {
                    onNavigateToLoginChild.invoke()

                }
            )
        }
    }





    BaseColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState()),
        state = uiState

    ) {
        Text(
            text = stringResource(id = R.string.common_register_uppercase),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = marginExtraLarge, top = marginExtraLarge),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(vertical = pageDividerMedium))
        CustomTextField(
            horizontalMargin = marginMedium,
            textFieldType = TextFieldType.NAME,
            onValueChange = {
                name = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = marginSmall))
        CustomTextField(
            horizontalMargin = marginMedium,
            textFieldType = TextFieldType.SURNAME,
            onValueChange = {
                surname = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = marginSmall))
        CustomTextField(
            horizontalMargin = marginMedium,
            textFieldType = TextFieldType.EMAIL,
            onValueChange = {
                email = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = marginSmall))
        CustomTextField(
            horizontalMargin = marginMedium,
            textFieldType = TextFieldType.PASSWORD,
            onValueChange = {
                password = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = marginSmall))
        CustomTextField(
            horizontalMargin = marginMedium,
            textFieldType = TextFieldType.PASSWORD_AGAIN,
            onValueChange = {
                passwordAgain = it
            },
        )

        Spacer(modifier = Modifier.padding(top = marginExtraLarge))
        CustomButton(
            buttonType = ButtonType.REGISTER,
            horizontalMargin = marginSmall,
            onClick = { register() })
        Spacer(modifier = Modifier.padding(top = marginExtraLarge))
        observeData()


    }


}





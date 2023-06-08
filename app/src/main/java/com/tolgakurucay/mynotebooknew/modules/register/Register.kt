package com.tolgakurucay.mynotebooknew.modules.register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.base.arePasswordsSame
import com.tolgakurucay.mynotebooknew.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.theme.spacing10
import com.tolgakurucay.mynotebooknew.theme.spacing30
import com.tolgakurucay.mynotebooknew.theme.spacing40
import com.tolgakurucay.mynotebooknew.theme.spacing5
import com.tolgakurucay.mynotebooknew.theme.spacing70


@Preview
@Composable
fun Register(
    viewModel: RegisterViewModel = hiltViewModel(),
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        RegisterContent()

    }

}


@Preview
@Composable
fun RegisterContent() {

    var email by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }
    var passwordAgain by remember { mutableStateOf<String?>(null) }
    var name by remember { mutableStateOf<String?>(null) }
    var surname by remember { mutableStateOf<String?>(null) }

    var isShowEmptyFieldsAlert by remember { mutableStateOf(false) }
    var isShowPasswordAlert by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.common_register_uppercase),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = spacing30, top = spacing30),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.padding(vertical = spacing70))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.NAME,
            onValueChange = {
                name = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.SURNAME,
            onValueChange = {
                surname = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.EMAIL,
            onValueChange = {
                email = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.PASSWORD,
            onValueChange = {
                password = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.PASSWORD_AGAIN,
            onValueChange = {
                passwordAgain = it
            },
        )

        Spacer(modifier = Modifier.padding(top = spacing40))
        Button(
            onClick = {
                Log.d("bilgitolga", "email: $email")
                Log.d("bilgitolga", "password: $password")
                Log.d("bilgitolga", "again: $passwordAgain")
                Log.d("bilgitolga", "name: $name")
                Log.d("bilgitolga", "surname: $surname")

                if (validateCustomTextFields(
                        arrayOf(
                            email,
                            password,
                            passwordAgain,
                            name,
                            surname
                        )
                    )
                ) {
                    if (arePasswordsSame(password = password, passwordAgain = passwordAgain)) {
                        //Viewmodel processes
                    } else {
                        isShowPasswordAlert = true
                    }

                } else {
                    isShowEmptyFieldsAlert = true
                }
            }, modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
                .clickable {

                }
                .padding(horizontal = spacing10)
        ) {
            Text(text = stringResource(id = R.string.common_login_now_uppercase))
        }
        Spacer(modifier = Modifier.padding(top = spacing40))

        if (isShowEmptyFieldsAlert) {
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                titleRes = R.string.common_information,
                descriptionRes = R.string.action_fill_empty_fields_correctly,
                onConfirm = {
                    isShowEmptyFieldsAlert = false
                },
            )
        }
        else if(isShowPasswordAlert){
            CustomAlertDialog(
                type = AlertDialogType.OKAY,
                titleRes = R.string.common_information,
                descriptionRes = R.string.common_passwords_not_same,
                onConfirm = {
                    isShowPasswordAlert = false
                },
            )
        }


    }


}
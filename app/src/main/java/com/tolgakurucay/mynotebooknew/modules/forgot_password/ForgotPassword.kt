package com.tolgakurucay.mynotebooknew.modules.forgot_password

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
import com.tolgakurucay.mynotebooknew.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.theme.spacing10
import com.tolgakurucay.mynotebooknew.theme.spacing30
import com.tolgakurucay.mynotebooknew.theme.spacing40
import com.tolgakurucay.mynotebooknew.theme.spacing70

@Preview
@Composable
fun ForgotPassword(viewModel: ForgotPasswordViewModel = hiltViewModel()) {
    Surface(Modifier.fillMaxSize()) {
        ForgotPasswordContent()
    }
}

@Preview
@Composable
fun ForgotPasswordContent() {

    var email by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(id = R.string.common_forgot_password),
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
        Button(onClick = {

        }, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxSize()
            .padding(horizontal = spacing10)) {
            Text(text = stringResource(id = R.string.common_login_now_uppercase))
        }
        Spacer(modifier = Modifier.padding(top = spacing40))




    }
}
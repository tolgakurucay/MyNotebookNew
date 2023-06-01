package com.tolgakurucay.mynotebooknew.modules.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tolgakurucay.mynotebooknew.CustomTextField
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.TextFieldType
import com.tolgakurucay.mynotebooknew.theme.Black
import com.tolgakurucay.mynotebooknew.theme.size1
import com.tolgakurucay.mynotebooknew.theme.size10
import com.tolgakurucay.mynotebooknew.theme.size5
import com.tolgakurucay.mynotebooknew.theme.spacing10
import com.tolgakurucay.mynotebooknew.theme.spacing16
import com.tolgakurucay.mynotebooknew.theme.spacing18
import com.tolgakurucay.mynotebooknew.theme.spacing20
import com.tolgakurucay.mynotebooknew.theme.spacing30
import com.tolgakurucay.mynotebooknew.theme.spacing35
import com.tolgakurucay.mynotebooknew.theme.spacing5

@Preview
@Composable
fun Login(
    viewModel: LoginViewModel = viewModel()
) {

    Surface(
        Modifier
            .fillMaxSize()
    ) {
        LoginContent(modifier = Modifier.fillMaxSize())
    }

}


@Composable
fun LoginContent(
    modifier: Modifier
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(id = R.string.common_login_uppercase),
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = spacing30, top = spacing30),
            style = MaterialTheme.typography.titleLarge
        )


        Spacer(modifier = Modifier.padding(vertical = spacing35))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.EMAIL, value = email,
            onValueChange = {
                email = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.PASSWORD, value = password,
            onValueChange = {
                password = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing20))
        Text(
            text = stringResource(id = R.string.common_did_you_forgot_password),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.padding(top = spacing18))
        Button(onClick = { }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = stringResource(id = R.string.common_login_now_uppercase))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing16),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.common_dont_you_have_an_account),
                modifier = Modifier.padding(start = spacing30),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.common_register),
                modifier = Modifier.padding(end = spacing30),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Divider(thickness = size5, color = Black, modifier = Modifier.fillMaxWidth(0.30f).align(Alignment.CenterVertically))
            Divider(thickness = size5, color = Black, modifier = Modifier.fillMaxWidth(0.30f).align(Alignment.CenterVertically))

        }



    }

}
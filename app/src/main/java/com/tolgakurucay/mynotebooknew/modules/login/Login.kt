package com.tolgakurucay.mynotebooknew.modules.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tolgakurucay.mynotebooknew.CustomTextField
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.TextFieldType
import com.tolgakurucay.mynotebooknew.theme.spacing20
import com.tolgakurucay.mynotebooknew.theme.spacing30
import com.tolgakurucay.mynotebooknew.theme.spacing35

@Preview
@Composable
fun Login(
    viewModel: LoginViewModel = viewModel()
) {

    Surface(Modifier.fillMaxSize()) {
        LoginContent(modifier = Modifier.fillMaxSize())
    }

}


@Composable
fun LoginContent(
    modifier: Modifier
) {

    Column(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = stringResource(id = R.string.common_login_now_uppercase),
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = spacing30, top = spacing30),
            style = MaterialTheme.typography.titleLarge
        )

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Spacer(modifier = Modifier.padding(vertical = spacing35))
        CustomTextField(
            textFieldType = TextFieldType.PASSWORD, value = email,
            onValueChange = {
                email = it
            },
        )


        Spacer(modifier = Modifier.padding(vertical = spacing20))


    }

}
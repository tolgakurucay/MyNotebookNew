package com.tolgakurucay.mynotebooknew.presentation.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.Black
import com.tolgakurucay.mynotebooknew.presentation.theme.size125
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing10
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing16
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing18
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing20
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing30
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing32
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing40
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing5
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing70
import com.tolgakurucay.mynotebooknew.util.safeLet
import com.tolgakurucay.mynotebooknew.util.showLog


@Composable
fun Login(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegisterMain: () -> Unit = {},
    onNavigateToForgotPasswordMain: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {


    val state = viewModel.state.collectAsStateWithLifecycle()

    if (state.value.isUserAuthenticated == true) {
        LaunchedEffect(
            key1 = "navigate",
            block = {
                onNavigateToHome.invoke()
            },
        )
    }
    else{
        showLog("girildimiiiiii")
    }


    Surface(
        Modifier
            .fillMaxSize()
    ) {
        LoginContent(
            onNavigateToRegisterContent = {
                onNavigateToRegisterMain.invoke()
            },
            onNavigateToForgotPasswordContent = {
                onNavigateToForgotPasswordMain.invoke()
            },
            state = state.value,
            signInWithEmailAndPassword = {
                viewModel.signInWithEmailAndPassword(it.email, it.password)
            }
        )
    }

}

@Preview
@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    onNavigateToRegisterContent: () -> Unit = {},
    onNavigateToForgotPasswordContent: () -> Unit = {},
    state: LoginState = LoginState(),
    signInWithEmailAndPassword: (SignInEmailPasswordRequest) -> Unit = {}

) {


    var email by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }



    BaseColumn(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState()),
        state = state,

        ) {

        Text(
            text = stringResource(id = R.string.common_login_uppercase),
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = spacing30, top = spacing30),
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
        Spacer(modifier = Modifier.padding(vertical = spacing5))
        CustomTextField(
            horizontalMargin = spacing10,
            textFieldType = TextFieldType.PASSWORD,
            onValueChange = {
                password = it
            },
        )
        Spacer(modifier = Modifier.padding(vertical = spacing20))
        Text(
            text = stringResource(id = R.string.common_did_you_forgot_password),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onNavigateToForgotPasswordContent.invoke()
                }, style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.padding(top = spacing18))

        CustomButton(
            buttonType = ButtonType.LOGIN, horizontalMargin = spacing10,
            onClick = {
                if ((arrayOf(email, password).validateCustomTextFields())) {
                    safeLet(email, password) { p1, p2 ->
                        signInWithEmailAndPassword.invoke(SignInEmailPasswordRequest(p1, p2))
                    }
                }
            },
        )
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
                modifier = Modifier
                    .padding(end = spacing30)
                    .clickable {
                        onNavigateToRegisterContent.invoke()
                    },
                style = MaterialTheme.typography.bodyMedium
            )


        }
        Spacer(modifier = Modifier.padding(top = spacing32))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(color = Black, modifier = Modifier.width(size125))
            Text(
                text = stringResource(id = R.string.common_or_sign_in_with),
                style = MaterialTheme.typography.bodySmall
            )
            Divider(color = Black, modifier = Modifier.width(size125))

        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing40, bottom = spacing40)
        ) {
            IconButton(
                onClick = {}, modifier = Modifier
                    .paint(painterResource(id = R.drawable.facebook_sign))
            ) {

            }
            IconButton(
                onClick = {},
                modifier = Modifier.paint(painterResource(id = R.drawable.google_sign))
            ) {

            }
            IconButton(
                onClick = {},
                modifier = Modifier.paint(painterResource(id = R.drawable.phone_sign))
            ) {

            }
        }

        //  listenEvents()

    }

}
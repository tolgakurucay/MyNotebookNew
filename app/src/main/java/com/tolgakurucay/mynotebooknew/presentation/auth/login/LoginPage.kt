package com.tolgakurucay.mynotebooknew.presentation.auth.login

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.data.database.Constant
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.domain.model.auth.SignInEmailPasswordRequest
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.Black
import com.tolgakurucay.mynotebooknew.presentation.theme.marginExtraLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.marginSmall
import com.tolgakurucay.mynotebooknew.presentation.theme.pageDividerMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.pageDividerLarge
import com.tolgakurucay.mynotebooknew.util.isNotNull
import com.tolgakurucay.mynotebooknew.util.safeLet
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue
import com.tolgakurucay.mynotebooknew.util.showLog


@Composable
fun LoginPage(
    context: Context = LocalContext.current,
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegisterMain: () -> Unit = {},
    onNavigateToForgotPasswordMain: () -> Unit = {},
    onNavigateToHome: () -> Unit = {}
) {


    val state by viewModel.state.collectAsStateWithLifecycle()


    if (state.isUserAuthenticated == true) {
        LaunchedEffect(
            key1 = "navigate",
            block = {
                onNavigateToHome.invoke()
            },
        )
    }

    if (state.googleAuthResult?.user.isNotNull()) {
        LaunchedEffect(key1 = "navigate2", block = { onNavigateToHome.invoke() })

    }

    val isShowError = remember { mutableStateOf(false) }

    if (isShowError.value) {
        CustomAlertDialog(
            type = AlertDialogType.OKAY,
            descriptionText = stringResource(id = R.string.error_not_logged_in_with_google),
            onConfirm = {
                isShowError.setStateFalse()
            },
        )
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(result.idToken, null)
                viewModel.signInWithGoogle(credential)
            } catch (it: ApiException) {
                showLog(it.localizedMessage)
                isShowError.setStateTrue()
            }
        },
    )


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
            state = state,
            signInWithEmailAndPassword = {
                viewModel.signInWithEmailAndPassword(it.email, it.password)
            }, onGoogleSignInClicked = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(Constant.ServerClientId)
                    .requestProfile()
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
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
    signInWithEmailAndPassword: (SignInEmailPasswordRequest) -> Unit = {},
    onGoogleSignInClicked: () -> Unit = {}
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
                .padding(end = marginExtraLarge, top = marginExtraLarge),
            style = MaterialTheme.typography.displaySmall
        )


        Spacer(modifier = Modifier.padding(vertical = pageDividerMedium))
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
        Spacer(modifier = Modifier.padding(vertical = marginLarge))
        Text(
            text = stringResource(id = R.string.common_did_you_forgot_password),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onNavigateToForgotPasswordContent.invoke()
                }, style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.padding(top = marginLarge))

        CustomButton(
            buttonType = ButtonType.LOGIN, horizontalMargin = marginMedium,
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
                .padding(top = marginLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.common_dont_you_have_an_account),
                modifier = Modifier.padding(start = marginExtraLarge),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.common_register),
                modifier = Modifier
                    .padding(end = marginLarge)
                    .clickable {
                        onNavigateToRegisterContent.invoke()
                    },
                style = MaterialTheme.typography.bodyMedium
            )


        }
        Spacer(modifier = Modifier.padding(top = marginExtraLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Divider(color = Black, modifier = Modifier.width(pageDividerLarge))
            Text(
                text = stringResource(id = R.string.common_or_sign_in_with),
                style = MaterialTheme.typography.bodySmall
            )
            Divider(color = Black, modifier = Modifier.width(pageDividerLarge))

        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = marginExtraLarge)
        ) {
            /*IconButton(
                onClick = {}, modifier = Modifier
                    .paint(painterResource(id = R.drawable.facebook_sign))
            ) {

            }*/
            IconButton(
                onClick = {
                    onGoogleSignInClicked()
                },
                modifier = Modifier.paint(painterResource(id = R.drawable.google_sign))
            ) {

            }
          /*  IconButton(
                onClick = {},
                modifier = Modifier.paint(painterResource(id = R.drawable.phone_sign))
            ) {

            }*/
        }

    }

}
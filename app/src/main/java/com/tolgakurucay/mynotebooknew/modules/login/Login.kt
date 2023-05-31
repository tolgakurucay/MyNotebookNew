package com.tolgakurucay.mynotebooknew.modules.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.theme.spacing5

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
//    modifier = modifier.windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal))
    Column(modifier = modifier) {

        Text(
            text = stringResource(id = R.string.common_login_now_uppercase),
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth(),
        )
        Text(text = "ljdsanfkjshdlkfj", modifier = Modifier.fillMaxWidth())
        Text(text = "ljdsanfkjshdlkfj")
        Text(text = "ljdsanfkjshdlkfj")
        Text(text = "ljdsanfkjshdlkfj")
        Text(text = "ljdsanfkjshdlkfj")
        Text(text = "ljdsanfkjshdlkfj", modifier = Modifier.padding(start = spacing5))

    }

}
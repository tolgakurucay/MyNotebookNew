package com.tolgakurucay.mynotebooknew.presentation.main.profile

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfilePage(viewModel: ProfileViewModel = hiltViewModel(), onBackPressed: () -> Unit) {

    LaunchedEffect(
        key1 = Unit,
        block = { viewModel.getProfileInformations() }
    )

    ProfileContent(
        viewModel.state.collectAsStateWithLifecycle().value,
        onBackPressed = onBackPressed
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showSystemUi = true)
fun ProfileContent(
    state: ProfileState = ProfileState(),
    pagerState: PagerState = rememberPagerState() { 3 },
    onBackPressed: () -> Unit = {},
    onUpdateClicked: () -> Unit = {}
) {

    val isSelectedEnglish = remember {
        mutableStateOf<Boolean?>(null)
    }

    when (isSelectedEnglish.value) {
        true -> Toast.makeText(LocalContext.current, "İlgilizce seçildi", Toast.LENGTH_SHORT).show()
        false -> Toast.makeText(LocalContext.current, "Türkçe seçildi", Toast.LENGTH_SHORT).show()
        else->{}
    }


    BaseScaffold(
        state = state,
        topBar = { ProfileTopBar(rights = state.rights, onBackPressed = onBackPressed) }) {

        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_black),
                    contentDescription = stringResource(id = R.string.cd_profile_picture),
                    modifier = Modifier
                        .size(125.dp)
                        .align(Alignment.CenterVertically)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = state.profileResponse.name.toString(), maxLines = 1)
                    Text(text = state.profileResponse.surname.toString(), maxLines = 1)
                    Text(text = state.profileResponse.mail.toString(), maxLines = 2)
                    Text(text = state.profileResponse.phoneNumber.toString(), maxLines = 1)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            CustomButton(buttonType = ButtonType.UPDATE, horizontalMargin = 16.dp) {
                onUpdateClicked.invoke()
            }

            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {


                when (it) {
                    0 -> {
                        ChangeLanguagePage(
                            onEnglishSelected = {
                                isSelectedEnglish.value = true
                            },
                            onTurkishSelected = {
                                isSelectedEnglish.value = false
                            },
                        )
                    }

                    1 -> {
                        Text(text = "deneme1")
                    }

                    2 -> {
                       WhoAmIPage()
                    }
                }
                //  Text(text = "deneme")
            }

        }


    }
}


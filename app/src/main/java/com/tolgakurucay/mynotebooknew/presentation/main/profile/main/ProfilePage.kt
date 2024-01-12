package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.main.profile.change_language.ChangeLanguagePage
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.LightDarkModeScreen
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.ViewMode
import com.tolgakurucay.mynotebooknew.presentation.main.profile.who_am_i.WhoAmIPage
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import com.tolgakurucay.mynotebooknew.util.setCurrentLanguage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfilePage(
    viewModel: ProfileViewModel = hiltViewModel(),
    onViewModeChanged: (viewMode: ViewMode) -> Unit = {},
    onBackPressed: () -> Unit,
) {

    val context: Context = LocalContext.current
    val appLanguage = remember {
        mutableStateOf<AppLanguage?>(null)
    }

    LaunchedEffect(key1 = appLanguage.value){
        appLanguage.value?.let { safeAppLanguage ->
            context.setCurrentLanguage(safeAppLanguage)
            viewModel.setLanguageTagToDataStore(safeAppLanguage)
            onBackPressed.invoke()
        }
    }

    LaunchedEffect(
        key1 = Unit,
        block = { viewModel.getProfileInformations() }
    )


    ProfileContent(
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onBackPressed = onBackPressed,
        onViewModeChanged = {
            viewModel.setViewModeToDataStore(it)
            onViewModeChanged.invoke(it)
        },
        onLanguageChanged = {
            appLanguage.value = it
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showSystemUi = true)
fun ProfileContent(
    state: ProfileState = ProfileState(),
    pagerState: PagerState = rememberPagerState() { 3 },
    onBackPressed: () -> Unit = {},
    onUpdateClicked: () -> Unit = {},
    onLanguageChanged: (lng: AppLanguage) -> Unit = {},
    onViewModeChanged: (viewMode: ViewMode) -> Unit = {}
) {



    BaseScaffold(
        state = state,
        topBar = { ProfileTopBar(rights = state.rights, onBackPressed = onBackPressed) }) { it ->

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
            ) { pageNumber ->


                when (pageNumber) {
                    0 -> {
                        ChangeLanguagePage(
                            onLanguageChanged = {
                               onLanguageChanged.invoke(it)
                            }
                        )
                    }

                    1 -> {
                        LightDarkModeScreen(state.viewMode){ viewMode ->
                            onViewModeChanged.invoke(viewMode)
                        }
                    }

                    2 -> {
                        WhoAmIPage()
                    }
                }
            }

        }


    }
}


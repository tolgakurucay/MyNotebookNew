package com.tolgakurucay.mynotebooknew.presentation.main.profile.main

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.DeveloperBoard
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.twotone.DarkMode
import androidx.compose.material.icons.twotone.DeveloperBoard
import androidx.compose.material.icons.twotone.Language
import androidx.compose.material.icons.twotone.Payment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.main.profile.change_language.ChangeLanguagePage
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.LightDarkModeScreen
import com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode.ViewMode
import com.tolgakurucay.mynotebooknew.presentation.main.profile.payment.PaymentPage
import com.tolgakurucay.mynotebooknew.presentation.main.profile.who_am_i.WhoAmIPage
import com.tolgakurucay.mynotebooknew.presentation.theme.LightOrange
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import com.tolgakurucay.mynotebooknew.util.makeSmallerBitmap
import com.tolgakurucay.mynotebooknew.util.setCurrentLanguage
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue
import com.tolgakurucay.mynotebooknew.util.toBase64
import com.tolgakurucay.mynotebooknew.util.toBitmap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfilePage(
    viewModel: ProfileViewModel = hiltViewModel(),
    onViewModeChanged: (viewMode: ViewMode) -> Unit = {},
    onBackPressed: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val context: Context = LocalContext.current
    val appLanguage = remember {
        mutableStateOf<AppLanguage?>(null)
    }

    val isShowUpdateDialog = remember {
        mutableStateOf(false)
    }

    val profilePhoto = remember {
        mutableStateOf<String?>(null)
    }


    if (isShowUpdateDialog.value) {
        ProfileUpdateDialog(profileResponse = state.value.profileResponse,
            onCancelClicked = { isShowUpdateDialog.setStateFalse() },
            onUpdate = {
                profilePhoto.value = it.photo
                viewModel.updateProfileInformation(it)
            })
    }

    LaunchedEffect(key1 = appLanguage.value) {
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
        state = state.value,
        onBackPressed = onBackPressed,
        onViewModeChanged = {
            viewModel.setViewModeToDataStore(it)
            onViewModeChanged.invoke(it)
        },
        onLanguageChanged = {
            appLanguage.value = it
        },
        onUpdateClicked = {
            isShowUpdateDialog.setStateTrue()
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showSystemUi = true)
fun ProfileContent(
    state: ProfileState = ProfileState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    pagerState: PagerState = rememberPagerState() { 4 },
    onBackPressed: () -> Unit = {},
    onUpdateClicked: () -> Unit = {},
    onLanguageChanged: (lng: AppLanguage) -> Unit = {},
    onViewModeChanged: (viewMode: ViewMode) -> Unit = {}
) {

    val context = LocalContext.current
    val tabItems = listOf(
        ProfileTabItem(
            title = stringResource(id = R.string.profile_bottom_item_language),
            unselectedIcon = Icons.Outlined.Language,
            selectedIcon = Icons.TwoTone.Language
        ), ProfileTabItem(
            title = stringResource(id = R.string.profile_bottom_item_payment),
            unselectedIcon = Icons.Outlined.Payment,
            selectedIcon = Icons.TwoTone.Payment
        ), ProfileTabItem(
            title = stringResource(id = R.string.profile_bottom_item_view_mode),
            unselectedIcon = Icons.Outlined.DarkMode,
            selectedIcon = Icons.TwoTone.DarkMode
        ), ProfileTabItem(
            title = stringResource(id = R.string.profile_bottom_item_who_am_i),
            unselectedIcon = Icons.Outlined.DeveloperBoard,
            selectedIcon = Icons.TwoTone.DeveloperBoard
        )
    )

    BaseScaffold(
        state = state,
        topBar = { ProfileTopBar(rights = state.rights, onBackPressed = onBackPressed) }) { it ->

        Column(modifier = Modifier.padding(it)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.profileResponse.photo.isNullOrEmpty().not()) {
                    Image(
                        bitmap = state.profileResponse.photo
                            .toBitmap()!!.asImageBitmap(),
                        contentDescription = stringResource(id = R.string.cd_profile_picture),
                        modifier = Modifier
                            .size(125.dp)
                            .align(Alignment.CenterVertically)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.profile_black),
                        contentDescription = stringResource(id = R.string.cd_profile_picture),
                        modifier = Modifier
                            .size(125.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = state.profileResponse.name ?: stringResource(id = R.string.no_name),
                        maxLines = 1
                    )
                    Text(
                        text = state.profileResponse.surname
                            ?: stringResource(id = R.string.no_surname), maxLines = 1
                    )
                    Text(
                        text = state.profileResponse.mail ?: stringResource(id = R.string.no_mail),
                        maxLines = 2
                    )
                    Text(
                        text = state.profileResponse.phoneNumber
                            ?: stringResource(id = R.string.no_phone), maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            CustomButton(buttonType = ButtonType.UPDATE, horizontalMargin = 16.dp) {
                onUpdateClicked.invoke()
            }

            val onBackgroundColor = MaterialTheme.colorScheme.onSecondary

            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(8.dp)
                    .drawBehind {
                        drawRoundRect(
                            color = onBackgroundColor,
                            cornerRadius = CornerRadius(16f, 16f)
                        )
                    }
                    .padding(2.dp)
                    .drawBehind {
                        drawRoundRect(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Yellow,
                                    LightOrange,
                                    Color.Blue,
                                    Color.Green,
                                    Color.Red
                                )
                            ), style = Stroke(
                                width = 8f, pathEffect = PathEffect.dashPathEffect(
                                    floatArrayOf(16f, 16f), 0f
                                )
                            ),
                            cornerRadius = CornerRadius(10f, 10f)
                        )
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
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
                        PaymentPage { paymentType ->
                            Toast.makeText(context, paymentType.name, Toast.LENGTH_SHORT).show()

                        }
                    }

                    2 -> {
                        LightDarkModeScreen(state.viewMode) { viewMode ->
                            onViewModeChanged.invoke(viewMode)
                        }
                    }

                    3 -> {
                        WhoAmIPage()
                    }
                }
            }

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.align(Alignment.End)
            ) {
                tabItems.forEachIndexed { index, profileTabItem ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = profileTabItem.title,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }, icon = {
                            Icon(
                                imageVector = if (pagerState.currentPage == index) profileTabItem.selectedIcon else profileTabItem.unselectedIcon,
                                contentDescription = null
                            )
                        }
                    )
                }
            }


        }


    }
}

data class ProfileTabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)


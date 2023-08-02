package com.tolgakurucay.mynotebooknew.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.theme.radius30

@Preview
@Composable
fun HomeBottomBar(
    homeNavigation: (HomeNavigations) -> Unit = {},
) {
    BottomAppBar(

        actions = {
            IconButton(onClick = { homeNavigation.invoke(HomeNavigations.FAVORITES) }) {
                Icon(
                    painter = painterResource(id = R.drawable.star_black),
                    contentDescription = stringResource(
                        id = R.string.cd_favorite
                    ),
                )
            }
            IconButton(onClick = { homeNavigation.invoke(HomeNavigations.CLOUD) }) {
                Icon(
                    painter = painterResource(id = R.drawable.cloud_black),
                    contentDescription = stringResource(
                        id = R.string.cd_cloud
                    ),
                )
            }
            IconButton(onClick = { homeNavigation.invoke(HomeNavigations.PROFILE) }) {
                Icon(
                    painter = painterResource(id = R.drawable.profile_black),
                    contentDescription = stringResource(
                        id = R.string.cd_profile
                    ),
                )
            }
            IconButton(onClick = { homeNavigation.invoke(HomeNavigations.LOGOUT) }) {
                Icon(
                    painter = painterResource(id = R.drawable.logout_black),
                    contentDescription = stringResource(
                        id = R.string.cd_logout
                    ),
                )
            }


        },
        modifier = Modifier.clip(
            RoundedCornerShape(topStart = radius30, topEnd = radius30)
        ),
        floatingActionButton = {
            FloatingActionButton(onClick = { homeNavigation.invoke(HomeNavigations.ADD_NOTE) }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus_black),
                    contentDescription = stringResource(
                        id = R.string.cd_add_note
                    ),
                )
            }
        }

    )
}


enum class HomeNavigations {
    FAVORITES,
    CLOUD,
    PROFILE,
    LOGOUT,
    ADD_NOTE
}
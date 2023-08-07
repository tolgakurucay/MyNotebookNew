package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlarm
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.presentation.theme.Black
import com.tolgakurucay.mynotebooknew.presentation.theme.LightOrange

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddNoteTopBar(onBackPressed: () -> Unit = {}, addNoteState: (AddNoteState) -> Unit = {}) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.screen_add_note),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow_black),
                contentDescription = stringResource(
                    id = R.string.cd_navigate_to_home
                ),
                modifier = Modifier.clickable { onBackPressed.invoke() }
            )
        },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            DropDownMenu()
        }
    )
}


@Composable
@Preview
private fun DropDownMenu(addNoteState: (AddNoteState) -> Unit = {}) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val state by remember { mutableStateOf(AddNoteState()) }

    var alarmMessage by remember { mutableStateOf(context.getString(R.string.action_set_an_alarm)) }
    var cloudMessage by remember { mutableStateOf(context.getString(R.string.action_add_to_cloud)) }
    var favoriteMessage by remember { mutableStateOf(context.getString(R.string.action_add_favorite)) }

    var alarmColor by remember { mutableStateOf(Black) }
    var cloudColor by remember { mutableStateOf(Black) }
    var favoriteColor by remember { mutableStateOf(Black) }




    IconButton(onClick = { expanded = !expanded }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.cd_more)
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {

        //Alarm item
        DropdownMenuItem(
            text = { Text(text = alarmMessage) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AddAlarm,
                    contentDescription = stringResource(
                        id = R.string.cd_add_an_alarm
                    ),
                )
            },
            onClick = {
                if (state.isAddedAlarm) {
                    state.isAddedAlarm = false
                    alarmColor = Black
                    alarmMessage = context.getString(R.string.cd_add_an_alarm)
                } else {
                    state.isAddedAlarm = true
                    alarmColor = LightOrange
                    alarmMessage = context.getString(R.string.common_alarm_has_set)
                }
            },
            colors = MenuDefaults.itemColors(
                textColor = alarmColor,
                leadingIconColor = alarmColor
            )
        )

        //Favorite Item
        DropdownMenuItem(
            text = { Text(text = favoriteMessage) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(
                        id = R.string.cd_favorite
                    ),
                )
            },
            onClick = {
                if (state.isAddedFavorite) {
                    state.isAddedFavorite = false
                    favoriteColor = Black
                    favoriteMessage = context.getString(R.string.action_add_favorite)
                } else {
                    state.isAddedFavorite = true
                    favoriteColor = LightOrange
                    favoriteMessage = context.getString(R.string.common_added_to_favorite)
                }
            },
            colors = MenuDefaults.itemColors(
                textColor = favoriteColor,
                leadingIconColor = favoriteColor
            )
        )

        //Cloud Item
        DropdownMenuItem(
            text = { Text(text = cloudMessage) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Cloud,
                    contentDescription = stringResource(
                        id = R.string.cd_cloud
                    ),
                )
            },
            onClick = {
                if (state.isAddedCloud) {
                    state.isAddedCloud = false
                    cloudColor = Black
                    cloudMessage = context.getString(R.string.action_add_to_cloud)
                } else {
                    state.isAddedCloud = true
                    cloudColor = LightOrange
                    cloudMessage = context.getString(R.string.common_added_to_cloud)
                }
            },
            colors = MenuDefaults.itemColors(
                textColor = cloudColor,
                leadingIconColor = cloudColor
            )
        )
    }
}



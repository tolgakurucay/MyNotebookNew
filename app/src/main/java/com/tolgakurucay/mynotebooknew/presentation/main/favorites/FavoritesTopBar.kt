package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FavoritesTopBar(
    showingTheToolbar: Boolean = false,
    actions: (FavoritesTopBarActions) -> Unit = {}
) {

    val menuState = remember {
        mutableStateOf(false)
    }


    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.screen_favorites),
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow_black),
                contentDescription = stringResource(
                    id = R.string.cd_navigate_to_home
                ),
                modifier = Modifier.clickable { actions.invoke(FavoritesTopBarActions.BACK) }
            )
        },
        modifier = Modifier.fillMaxWidth(), actions = {

            if (showingTheToolbar) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_menu),
                    contentDescription = stringResource(
                        id = R.string.cd_menu
                    ),
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            menuState.value = menuState.value.not()
                        }
                )

                if (menuState.value) {
                    DropdownMenu(
                        expanded = menuState.value,
                        onDismissRequest = { menuState.value = menuState.value.not() }) {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.action_delete)) },
                            onClick = { actions.invoke(FavoritesTopBarActions.DELETE) })
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = R.string.action_remove_from_favorites)) },
                            onClick = { actions.invoke(FavoritesTopBarActions.REMOVE_FROM_FAVORITES) })

                    }
                }
            }
        }
    )
}


enum class FavoritesTopBarActions {
    BACK,
    DELETE,
    REMOVE_FROM_FAVORITES
}


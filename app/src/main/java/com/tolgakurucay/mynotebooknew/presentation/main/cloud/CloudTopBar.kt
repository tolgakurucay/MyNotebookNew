package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.util.setStateFalse

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun CloudTopBar(
    showingTheToolbar: Boolean = false,
    showItemsForOneAction: Boolean = false,
    actions: (CloudTopBarActions) -> Unit = {}
) {

    val menuState = remember {
        mutableStateOf(false)
    }

    val showingTheSearchBar = remember {
        mutableStateOf(false)
    }
    val searchedString = remember {
        mutableStateOf("")
    }

    CenterAlignedTopAppBar(
        title = {
            Box(modifier = Modifier.padding(horizontal = 4.dp)) {

                AnimatedVisibility(visible = showingTheSearchBar.value) {
                    TextField(
                        value = searchedString.value,
                        onValueChange = {
                            searchedString.value = it
                            actions.invoke(CloudTopBarActions.Search(it))
                        },
                        modifier = Modifier
                            .padding(horizontal = 4.dp, vertical = 8.dp)
                            .fillMaxWidth(0.7f),
                        maxLines = 2,
                        textStyle = MaterialTheme.typography.titleMedium,
                        colors = TextFieldDefaults.colors()
                    )
                }

                AnimatedVisibility(visible = showingTheSearchBar.value.not()) {
                    Text(
                        text = stringResource(id = R.string.cloud_page),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        modifier = Modifier
                            .basicMarquee()
                            .wrapContentWidth()
                    )
                }

            }

        },
        modifier = Modifier.fillMaxWidth(),
        actions = {
            if (showingTheSearchBar.value.not()) {
                Image(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = stringResource(
                        id = R.string.cd_search_icon
                    ), modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable {
                            showingTheSearchBar.value = showingTheSearchBar.value.not()
                        }
                        .size(30.dp)
                )
            }

            if (showingTheToolbar) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_menu),
                    contentDescription = stringResource(
                        id = R.string.cd_menu
                    ), modifier = Modifier
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
                            onClick = { actions.invoke(CloudTopBarActions.Delete) })
                        if (showItemsForOneAction) {
                            DropdownMenuItem(
                                text = { Text(text = stringResource(id = R.string.action_share)) },
                                onClick = { actions.invoke(CloudTopBarActions.Share) })
                        }


                    }
                }
            }


        },
        navigationIcon = {
            if (showingTheSearchBar.value) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_black),
                    contentDescription = stringResource(
                        id = R.string.cd_navigate_to_home
                    ),
                    modifier = Modifier.clickable {
                        if (showingTheSearchBar.value) showingTheSearchBar.setStateFalse()

                    }
                )
            }

        }
    )


}


sealed class CloudTopBarActions {
    object Delete : CloudTopBarActions()
    class Search(val searchString: String) : CloudTopBarActions()
    object Share : CloudTopBarActions()
}
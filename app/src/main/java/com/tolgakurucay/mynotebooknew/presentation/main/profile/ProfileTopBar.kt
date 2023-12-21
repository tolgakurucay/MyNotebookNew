package com.tolgakurucay.mynotebooknew.presentation.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.util.isNotNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ProfileTopBar(rights: Int? = null, onBackPressed: () -> Unit = {}) {

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.profile_page),
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
            if (rights.isNotNull()) Text(
                text = rights.toString(),
                modifier = Modifier.padding(end = 4.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_menu),
                contentDescription = stringResource(
                    id = R.string.cd_right
                )
            )
        }
    )
}
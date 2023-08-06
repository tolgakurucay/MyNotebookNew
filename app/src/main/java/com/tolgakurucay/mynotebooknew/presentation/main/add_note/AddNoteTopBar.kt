package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tolgakurucay.mynotebooknew.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteTopBar(onBackPressed: () -> Unit){
    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = R.string.screen_add_note), style = MaterialTheme.typography.titleLarge)
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
        modifier = Modifier.fillMaxWidth()
    )
}
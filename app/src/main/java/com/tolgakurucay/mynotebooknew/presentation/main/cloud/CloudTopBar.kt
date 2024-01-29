package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolgakurucay.mynotebooknew.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Preview
@Composable
fun CloudTopBar() {

    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.cloud_page),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .basicMarquee()

        )
    }, modifier = Modifier.fillMaxWidth())

}
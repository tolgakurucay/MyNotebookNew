package com.tolgakurucay.mynotebooknew.presentation.main.profile.light_dark_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.util.AppLanguage

@Preview
@Composable
fun LightDarkModeScreen(onViewModeChanged: (ViewMode) -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        val viewMode = remember {
            mutableStateOf<ViewMode?>(null)
        }

        LaunchedEffect(
            key1 = viewMode.value,
            block = {
                viewMode.value?.let { safeViewMode ->
                    onViewModeChanged.invoke(safeViewMode)
                }
            },
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = viewMode.value == ViewMode.DARK,
                    onClick = {
                        if (viewMode.value != ViewMode.DARK){
                            viewMode.value = ViewMode.DARK
                        }
                    },
                )
                Text(
                    text = stringResource(id = R.string.common_dark_mode),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            Spacer(modifier = Modifier.size(0.dp, 10.dp))

            Row(
                modifier = Modifier
                    .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = viewMode.value == ViewMode.LIGHT,
                    onClick = {
                        if (viewMode.value != ViewMode.LIGHT){
                            viewMode.value = ViewMode.LIGHT
                        }
                    },
                )
                Text(
                    text = stringResource(id = R.string.common_light_mode),
                    textAlign = TextAlign.Center, modifier = Modifier.padding(end = 10.dp)
                )
            }

        }








    }

}


enum class ViewMode {
    LIGHT,
    DARK
}
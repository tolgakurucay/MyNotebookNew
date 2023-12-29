package com.tolgakurucay.mynotebooknew.presentation.main.profile.who_am_i

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue

@Preview
@Composable
fun WhoAmIPage() {


    val isNavigateToLinkedin = remember {
        mutableStateOf(false)
    }

    val isNavigateToGithub = remember {
        mutableStateOf(false)
    }

    val isNavigateToMedium = remember {
        mutableStateOf(false)
    }

    if (isNavigateToLinkedin.value) {
        LocalContext.current.startActivity(
            Intent(Intent.ACTION_VIEW).also {
                it.data = "https://www.linkedin.com/in/tolgakurucay/".toUri()
            }
        )
        isNavigateToLinkedin.setStateFalse()
    }

    if (isNavigateToGithub.value) {
        LocalContext.current.startActivity(
            Intent(Intent.ACTION_VIEW).also {
                it.data = "https://github.com/tolgakurucay".toUri()
            }
        )
        isNavigateToGithub.setStateFalse()
    }

    if (isNavigateToMedium.value) {
        LocalContext.current.startActivity(
            Intent(Intent.ACTION_VIEW).also {
                it.data = "https://medium.com/@tolgakurucay".toUri()
            }
        )
        isNavigateToMedium.setStateFalse()
    }



    Box(modifier = Modifier.fillMaxWidth()) {

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.tolga),
                    contentDescription = stringResource(
                        id = R.string.cd_tolga_kurucay
                    ), modifier = Modifier.size(60.dp)
                )
                Text(
                    text = stringResource(id = R.string.common_tolga_kurucay),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge
                )
            }
            Text(
                text = stringResource(id = R.string.who_am_i_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .clickable { isNavigateToLinkedin.setStateTrue() },
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_linkedin),
                    contentDescription = stringResource(
                        id = R.string.cd_linkedin
                    ),
                )
                WhoAmIText(value = stringResource(id = R.string.common_linkedin))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable {
                        isNavigateToGithub.setStateTrue()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = stringResource(
                        id = R.string.cd_github
                    ),
                )
                WhoAmIText(value = stringResource(id = R.string.common_github))
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clickable {
                        isNavigateToMedium.setStateTrue()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_medium),
                    contentDescription = stringResource(
                        id = R.string.cd_medium
                    ),
                )
                WhoAmIText(stringResource(id = R.string.common_medium))

            }


        }


    }


}
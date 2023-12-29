package com.tolgakurucay.mynotebooknew.presentation.main.profile.change_language

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.data.database.DataStoreManager
import com.tolgakurucay.mynotebooknew.util.AppLanguage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Preview
@Composable
fun ChangeLanguagePage(
    onLanguageChanged: (language: AppLanguage)-> Unit = {},
) {
    val context = LocalContext.current
    val db = remember { DataStoreManager(context) }

    val isSelectedEnglish = remember {
        mutableStateOf<Boolean?>(null)
    }

    LaunchedEffect(Unit) {
        db.getLanguageTag().onEach { languageTag ->
            when (AppLanguage.valueOf(languageTag.uppercase())) {
                AppLanguage.TR -> isSelectedEnglish.value = false
                AppLanguage.EN -> isSelectedEnglish.value = true
            }
        }.collect()
    }




    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation_language_screen
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = preloaderLottieComposition,
                progress = preloaderProgress,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )



            Spacer(modifier = Modifier.size(0.dp, 10.dp))

            Row(
                modifier = Modifier
                    .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = isSelectedEnglish.value == false,
                    onClick = {
                        if (isSelectedEnglish.value == true) isSelectedEnglish.value = false
                        if (isSelectedEnglish.value == false) onLanguageChanged.invoke(AppLanguage.TR)
                    },
                )
                Text(
                    text = stringResource(id = R.string.common_turkish),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }



            Row(
                modifier = Modifier
                    .width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = isSelectedEnglish.value == true,
                    onClick = {
                        if (isSelectedEnglish.value == false) isSelectedEnglish.value = true
                        if (isSelectedEnglish.value == true) onLanguageChanged.invoke(AppLanguage.EN)
                    },
                )
                Text(
                    text = stringResource(id = R.string.common_english),
                    textAlign = TextAlign.Center, modifier = Modifier.padding(end = 10.dp)
                )

            }


        }

    }


}
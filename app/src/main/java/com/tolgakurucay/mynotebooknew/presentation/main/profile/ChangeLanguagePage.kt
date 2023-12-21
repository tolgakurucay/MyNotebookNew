package com.tolgakurucay.mynotebooknew.presentation.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.tolgakurucay.mynotebooknew.R

@Preview
@Composable
fun ChangeLanguagePage(
    onEnglishSelected: () -> Unit = {},
    onTurkishSelected: () -> Unit = {}
) {

    val isSelectedEnglish = remember {
        mutableStateOf(false)
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
                    selected = !isSelectedEnglish.value,
                    onClick = {
                        if (isSelectedEnglish.value) isSelectedEnglish.value = false

                        if (!isSelectedEnglish.value) onTurkishSelected.invoke()
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
                    selected = isSelectedEnglish.value,
                    onClick = {
                        if (isSelectedEnglish.value.not()) isSelectedEnglish.value = true
                        if (isSelectedEnglish.value) onEnglishSelected.invoke()
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
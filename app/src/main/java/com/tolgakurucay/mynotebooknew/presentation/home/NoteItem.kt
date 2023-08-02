package com.tolgakurucay.mynotebooknew.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.size50
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing15
import com.tolgakurucay.mynotebooknew.util.toSimpleString

@Preview
@Composable
fun NoteItem(model: NoteModel? = null) {

    Column {
        Row {
            Image(
                painter = painterResource(id = R.drawable.plus_black),
                contentDescription = stringResource(
                    id = R.string.cd_note_image
                ), modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(size50)
                    .height(size50)
            )
            Column {
                Text(text = model?.title ?: stringResource(id = R.string.common_not_titled))
                Text(
                    text = model?.description
                        ?: stringResource(id = R.string.common_not_added_description)
                )
            }
        }

        Text(
            text = model?.date?.toSimpleString() ?: "12.09.2000",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = spacing15)
        )

    }

}
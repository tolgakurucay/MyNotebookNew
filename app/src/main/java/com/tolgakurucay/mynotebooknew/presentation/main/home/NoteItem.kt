package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.LightOrange
import com.tolgakurucay.mynotebooknew.presentation.theme.radius10
import com.tolgakurucay.mynotebooknew.presentation.theme.size50
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing15
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing5
import com.tolgakurucay.mynotebooknew.util.toDate
import com.tolgakurucay.mynotebooknew.util.toSimpleString

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NoteItem(
    model: NoteModel? = null,
    onClicked: (NoteModel?) -> Unit = {},
    onLongClicked: (NoteModel?) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .padding(spacing5)
            .combinedClickable(
                onClick = { onClicked.invoke(model) },
                onLongClick = { onLongClicked.invoke(model) },
            )


    ) {

        Column(
            modifier = Modifier
                .border(
                    width = if (model?.isSelected == false) 1.dp else 3.dp,
                    color = if (model?.isSelected == false) LightOrange else Color.Green,
                    shape = RoundedCornerShape(radius10)
                )
                .fillMaxWidth()
                .padding(spacing5)

        ) {

            Row(modifier = Modifier.fillMaxWidth()) {
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
                    Text(
                        text = model?.title ?: stringResource(id = R.string.common_not_titled),
                        style = MaterialTheme.typography.titleMedium, maxLines = 2
                    )
                    Text(
                        text = model?.description
                            ?: stringResource(id = R.string.common_not_added_description),
                        style = MaterialTheme.typography.bodySmall, maxLines = 4
                    )
                }
            }

            Text(
                text = model?.date?.toDate()?.toSimpleString() ?: "12.09.2000",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = spacing15),
                style = MaterialTheme.typography.bodySmall
            )

        }

    }


}
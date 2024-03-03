package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageNotSupported
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.LightOrange
import com.tolgakurucay.mynotebooknew.presentation.theme.imageSizeMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.marginLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.marginSmall
import com.tolgakurucay.mynotebooknew.presentation.theme.strokeMedium
import com.tolgakurucay.mynotebooknew.presentation.theme.strokeThick
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
            .padding(marginSmall)
            .combinedClickable(
                onClick = { onClicked.invoke(model) },
                onLongClick = { onLongClicked.invoke(model) },
            )


    ) {
        Column(
            modifier = Modifier
                .border(
                    width = if (model?.isSelected == false) strokeMedium else strokeThick,
                    color = if (model?.isSelected == false) LightOrange else Color.Green,
                    shape = RoundedCornerShape(marginMedium)
                )
                .fillMaxWidth()
                .padding(marginSmall)

        ) {

            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = Icons.Filled.ImageNotSupported,
                    contentDescription = stringResource(
                        id = R.string.cd_note_image
                    ),
                    modifier = Modifier
                        .size(imageSizeMedium)

                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                    ) {
                    Text(
                        text = model?.title ?: stringResource(id = R.string.common_not_titled),
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2

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
                    .padding(top = marginLarge),
                style = MaterialTheme.typography.bodySmall
            )

        }

    }


}
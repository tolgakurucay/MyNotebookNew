package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing15
import com.tolgakurucay.mynotebooknew.util.toDate
import com.tolgakurucay.mynotebooknew.util.toSimpleString
import com.tolgakurucay.mynotebooknew.presentation.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CloudNoteItem(
    model: NoteModel? = null,
    onClicked: (NoteModel?) -> Unit = {},
    onLongClicked: (NoteModel?) -> Unit = {}
) {
    ElevatedCard(
        onClick = { }, shape = CardDefaults.elevatedShape, colors = CardDefaults.cardColors(
            LightBlue, Color.Red
        ), modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = model?.title ?: stringResource(id = R.string.common_not_titled),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Text(
                    text = model?.description
                        ?: stringResource(id = R.string.common_not_added_description),
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    modifier = Modifier.padding(top = 4.dp).padding(top = 16.dp)
                )

            }
            Column(modifier = Modifier.align(Alignment.TopEnd)) {
                Image(
                    painter = painterResource(id = R.drawable.plus_black),
                    contentDescription = stringResource(
                        id = R.string.cd_note_image
                    ), modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                )

                FloatingActionButton(
                    onClick = { }, modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.End)
                ) {
                    Icon(
                        imageVector = Icons.Filled.NavigateNext,
                        contentDescription = "",
                        Modifier.size(50.dp),
                    )
                }
            }
            Text(
                text = model?.date?.toDate()?.toSimpleString() ?: "12.09.2000",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(top = spacing15),
                style = MaterialTheme.typography.bodySmall
            )

        }

    }
}
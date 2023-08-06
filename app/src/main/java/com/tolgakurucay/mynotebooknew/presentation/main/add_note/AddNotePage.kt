package com.tolgakurucay.mynotebooknew.presentation.main.add_note

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing15
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing5

@Preview
@Composable
fun AddNotePage(viewModel: AddNoteViewModel = hiltViewModel(),onBackPressed: () -> Unit = {}) {

    AddNoteContent(viewModel, onBackPressed = onBackPressed)

}

@Composable
private fun AddNoteContent(viewModel: AddNoteViewModel, onBackPressed: () -> Unit) {

    var title by remember { mutableStateOf<String?>(null) }
    var description by remember { mutableStateOf<String?>(null) }



    BaseScaffold(
        viewModel = viewModel,
        topBar = { AddNoteTopBar(onBackPressed = onBackPressed) },
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = spacing15)
        ) {
            AnimatedVisibility(visible = false) {
                Image(painter = BitmapPainter(ImageBitmap.imageResource(id = R.drawable.back_arrow_black)), contentDescription = stringResource(id = R.string.cd_add_note), modifier = Modifier.fillMaxWidth(0.8f))

            }
            CustomTextField(
                textFieldType = TextFieldType.TITLE,
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = spacing5))
            CustomTextField(
                textFieldType = TextFieldType.DESCRIPTION,
                onValueChange = {
                    description = it
                },
            )
            Spacer(modifier = Modifier.padding(vertical = spacing5))
            CustomButton(buttonType = ButtonType.ADD_NOTE)

        }

    }

}
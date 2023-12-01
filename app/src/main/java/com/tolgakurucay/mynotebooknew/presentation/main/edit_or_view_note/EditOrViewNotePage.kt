package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note


import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing15
import com.tolgakurucay.mynotebooknew.presentation.theme.spacing5
import com.tolgakurucay.mynotebooknew.util.showLog

@Composable
fun EditOrViewNotePage(
    noteModel: NoteModel,
    viewModel: EditOrViewViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    EditOrViewNoteContent(
        model = noteModel,
        onBackPressed = onBackPressed,
        updateNoteToLocale = {
            viewModel.updateNoteFromLocale(it)
        },
        uiState = viewModel.state.collectAsStateWithLifecycle().value
    )

}

@Preview
@Composable
private fun EditOrViewNoteContent(
    model: NoteModel = NoteModel(),
    onBackPressed: () -> Unit = {},
    updateNoteToLocale: (NoteModel) -> Unit = {},
    uiState: EditOrViewState = EditOrViewState()
) {
    var title by remember { mutableStateOf(model.title) }
    var description by remember { mutableStateOf(model.description) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            // process eith the received image uri
        }

    if (uiState.hasUpdated) onBackPressed.invoke()




    BaseScaffold(
        state = uiState,
        topBar = {
            EditOrViewTopBar(
                onBackPressed = onBackPressed,
            )
        },
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = spacing15)
                .verticalScroll(rememberScrollState())
        ) {

            CustomTextField(
                textFieldType = TextFieldType.TITLE,
                defaultValue = title.toString(),
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = spacing5))
            CustomTextField(
                textFieldType = TextFieldType.DESCRIPTION,
                defaultValue = description.toString(),
                onValueChange = {
                    description = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = spacing5))
            CustomButton(
                buttonType = ButtonType.UPDATE_NOTE, horizontalMargin = spacing15,
                onClick = {
                    if (arrayOf(title, description).validateCustomTextFields()) {
                        updateNoteToLocale.invoke(
                            model.copy(title = title, description = description)
                        )
                    }
                },
            )

        }


    }


}

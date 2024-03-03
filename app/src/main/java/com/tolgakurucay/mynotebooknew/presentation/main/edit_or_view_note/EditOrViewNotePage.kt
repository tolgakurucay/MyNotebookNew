package com.tolgakurucay.mynotebooknew.presentation.main.edit_or_view_note

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.base.validateCustomTextFields
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomTextField
import com.tolgakurucay.mynotebooknew.presentation.custom.TextFieldType
import com.tolgakurucay.mynotebooknew.presentation.theme.marginLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginSmall

@Composable
fun EditOrViewNotePage(
    noteModel: NoteModel,
    viewModel: EditOrViewViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = state.hasDeleted) {
        if (state.hasDeleted) {
            onBackPressed.invoke()
        }
    }

    EditOrViewNoteContent(
        model = noteModel,
        onBackPressed = onBackPressed,
        updateNote = {
            viewModel.updateNote(it)
        },
        deleteNote = {
            viewModel.deleteNote(it)
        },
        uiState = state
    )

}

@Preview()
@Composable
private fun EditOrViewNoteContent(
    model: NoteModel = NoteModel(),
    onBackPressed: () -> Unit = {},
    updateNote: (NoteModel) -> Unit = {},
    deleteNote: (NoteModel) -> Unit = {},
    uiState: EditOrViewState = EditOrViewState()
) {
    var title by remember { mutableStateOf(model.title) }
    var description by remember { mutableStateOf(model.description) }
    var isShowDeleteNote by remember { mutableStateOf(false) }


    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            // process eith the received image uri
        }

    if (uiState.hasUpdated) onBackPressed.invoke()

    if (isShowDeleteNote) {
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            R.string.action_delete,
            stringResource(
                id = R.string.question_delete_note
            ),
            onConfirm = { deleteNote.invoke(model) },
            onDismiss = { isShowDeleteNote = false })
    }



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
                .padding(horizontal = marginLarge)
                .verticalScroll(rememberScrollState())
        ) {

            CustomTextField(
                textFieldType = TextFieldType.TITLE,
                defaultValue = title.toString(),
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = marginSmall))
            CustomTextField(
                textFieldType = TextFieldType.DESCRIPTION,
                defaultValue = description.toString(),
                onValueChange = {
                    description = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = marginSmall))
            CustomButton(
                buttonType = ButtonType.UPDATE_NOTE, horizontalMargin = marginLarge,
                onClick = {
                    if (arrayOf(title, description).validateCustomTextFields()) {
                        updateNote.invoke(
                            model.copy(title = title, description = description)
                        )
                    }
                },
            )
            Spacer(modifier = Modifier.padding(vertical = marginSmall))
            CustomButton(
                buttonType = ButtonType.DELETE_NOTE,
                horizontalMargin = marginLarge,
                onClick = {
                    isShowDeleteNote = true
                },
            )

        }


    }


}

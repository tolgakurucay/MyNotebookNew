package com.tolgakurucay.mynotebooknew.presentation.main.add_note

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
import com.tolgakurucay.mynotebooknew.presentation.theme.marginLarge
import com.tolgakurucay.mynotebooknew.presentation.theme.marginSmall

@Composable
fun AddNotePage(
    viewModel: AddNoteViewModel = hiltViewModel(),
    onBackPressed: () -> Unit = {},
    goToHome: () -> Unit = {}
) {

    AddNoteContent(
        onBackPressed = onBackPressed,
        goToHome = goToHome,
        saveNoteToLocale = {
            viewModel.saveNoteToLocalDatabase(it)
        },
        uiState = viewModel.state.collectAsStateWithLifecycle().value
    )
}

@Preview
@Composable
private fun AddNoteContent(
    onBackPressed: () -> Unit = {},
    goToHome: () -> Unit = {},
    saveNoteToLocale: (NoteModel) -> Unit = {},
    uiState: AddNoteState = AddNoteState()
) {
    var title by remember { mutableStateOf<String?>(null) }
    var description by remember { mutableStateOf<String?>(null) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uriList ->
            // process eith the received image uri
        }

    if (uiState.isAddedTheNote == true) goToHome.invoke(); uiState.isAddedTheNote = false




    BaseScaffold(
        state = uiState,
        topBar = {
            AddNoteTopBar(
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
                onValueChange = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.padding(vertical = marginSmall))
            CustomTextField(
                textFieldType = TextFieldType.DESCRIPTION,
                onValueChange = {
                    description = it
                },
            )
            Spacer(modifier = Modifier.padding(vertical = marginSmall))
            CustomButton(
                buttonType = ButtonType.ADD_NOTE, horizontalMargin = marginLarge,
                onClick = {
                    if (arrayOf(title, description).validateCustomTextFields()) {
                        saveNoteToLocale.invoke(
                            NoteModel(
                                title = title,
                                description = description,
                                date = System.currentTimeMillis()
                            )
                        )
                    }
                },
            )

        }


    }


}

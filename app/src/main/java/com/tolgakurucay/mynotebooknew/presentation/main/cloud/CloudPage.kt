package com.tolgakurucay.mynotebooknew.presentation.main.cloud

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue
import com.tolgakurucay.mynotebooknew.util.share

@Composable
fun CloudPage(
    cloudViewModel: CloudViewModel = hiltViewModel(), onNoteItemClicked: (NoteModel) -> Unit
) {

    val state by cloudViewModel.state.collectAsStateWithLifecycle()

    val isShowDeleteDialog = remember { mutableStateOf(false) }
    val isShared = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit){

    }


    CloudContent(state = state,
        onNoteItemClicked = { noteModel ->
            if (state.isShowingTheMenu.not()) {
                onNoteItemClicked.invoke(noteModel)
            } else {
                cloudViewModel.doSelectableOrNot(noteModel)
            }
        },
        onNoteItemLongClicked = { noteModel -> cloudViewModel.doSelectableOrNot(noteModel) },
        onTopBarActionsClicked = {
            when (it) {
                CloudTopBarActions.Delete -> isShowDeleteDialog.setStateTrue()
                is CloudTopBarActions.Search -> {
                    cloudViewModel.searchNotesByText(it.searchString)
                }
                CloudTopBarActions.Share -> isShared.setStateTrue()
            }
        })



    if (isShowDeleteDialog.value) {
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.common_information,
            descriptionText = stringResource(
                id = R.string.question_you_want_delete
            ),
            onConfirm = {
                cloudViewModel.deleteSelectedNotes()
                isShowDeleteDialog.setStateFalse()
            },
            onDismiss = {
                isShowDeleteDialog.setStateFalse()
            }
        )
    }

    if (isShared.value) {
        val model = state.noteList.find { it.isSelected }
        LocalContext.current.share(model?.title.toString(), model?.description.toString()) {
            isShared.setStateFalse()
        }
    }


}

@Preview
@Composable
fun CloudContent(
    state: CloudState = CloudState(),
    onTopBarActionsClicked: (CloudTopBarActions) -> Unit = {},
    onNoteItemClicked: (NoteModel) -> Unit = {},
    onNoteItemLongClicked: (NoteModel) -> Unit = {},
) {
    BaseScaffold(
        state = state,
        topBar = {
            CloudTopBar(
                showingTheToolbar = state.isShowingTheMenu,
                showItemsForOneAction = state.noteList.filter { it.isSelected }.size == 1,
                actions = onTopBarActionsClicked

            )
        }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if(state.noteList.any { it.noteType == NoteType.CLOUD.name }){
                Column(modifier = Modifier.padding(it)) {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(
                            horizontal = 15.dp, vertical = 5.dp
                        ),
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(state.noteList) { model ->
                            CloudNoteItem(
                                model,
                                onClicked = { noteModel ->
                                    noteModel?.let { safeNoteModel ->
                                        onNoteItemClicked.invoke(safeNoteModel)
                                    }
                                },
                                onLongClicked = { noteModel ->
                                    noteModel?.let { safeNoteModel ->
                                        onNoteItemLongClicked.invoke(safeNoteModel)
                                    }
                                },
                            )
                        }
                    }
                }
            }

            else{
                Text(
                    text = stringResource(id = R.string.cloud_no_content),
                    style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center
                )
            }
        }
    }
}
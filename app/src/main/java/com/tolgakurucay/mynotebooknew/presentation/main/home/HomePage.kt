package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog

@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit,
    onLogOutClicked: () -> Unit,
    onNoteItemClicked: (NoteModel) -> Unit
) {

    val observableState = viewModel.state.collectAsStateWithLifecycle()

    val isShowingExitDialog = remember { mutableStateOf(false) }
    val isShowingDeleteDialog = remember { mutableStateOf(false) }



    LaunchedEffect(key1 = Unit) {
        viewModel.getNotes()
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.removeAllSelectedItems()
        }
    }


    if (observableState.value.isUserLoggedOut == true) {
        LaunchedEffect(key1 = "LogOut") {
            onLogOutClicked.invoke()
        }
    }

    if (isShowingExitDialog.value) {
        CustomAlertDialog(
            type = AlertDialogType.YES_OR_NO,
            descriptionText = stringResource(id = R.string.question_you_want_exit),
            onConfirm = { viewModel.logOut() },
            onDismiss = { isShowingExitDialog.value = false })
    }

    if (isShowingDeleteDialog.value) {
        CustomAlertDialog(
            type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.action_delete,
            descriptionText = stringResource(
                id = R.string.question_you_want_delete
            ),
            onConfirm = { viewModel.deleteSelectedNotes() },
            onDismiss = { isShowingDeleteDialog.value = false },
        )
    }


    HomeContent(
        observableState.value,
        homeNavigations = homeNavigations,
        onLogOutClicked = {
            isShowingExitDialog.value = true
        },
        onNoteItemClicked = {noteModel->
            if(observableState.value.isShowingTheMenu.not()){
                onNoteItemClicked.invoke(noteModel)
            }
            else {
                viewModel.doSelectableOrNot(noteModel.copy(isSelected = noteModel.isSelected.not()))
            }

            },
        onNoteItemLongClicked = { viewModel.doSelectableOrNot(it) },
        onTopbarActionsClicked = {
            when (it) {
                HomeTopBarActions.DELETE -> isShowingDeleteDialog.value = true
                HomeTopBarActions.FAVORITE -> viewModel.addNotesToFavorite(observableState.value.notes.filter { it.isSelected })
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HomeContent(
    state: HomeState = HomeState(),
    homeNavigations: (HomeNavigations) -> Unit = {},
    onLogOutClicked: () -> Unit = {},
    onNoteItemClicked: (NoteModel) -> Unit = {},
    onNoteItemLongClicked: (NoteModel) -> Unit = {},
    onTopbarActionsClicked: (HomeTopBarActions) -> Unit = {}

) {


    BaseScaffold(
        state = state,
        topBar = {
            HomeTopBar(state.isShowingTheMenu, onTopbarActionsClicked)
        },
        bottomBar = {
            HomeBottomBar {
                if (it == HomeNavigations.LOGOUT) {
                    onLogOutClicked.invoke()
                } else {
                    homeNavigations.invoke(it)
                }
            }
        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.notes.any { it.noteType == NoteType.NOTE.name }) {
                    BaseColumn(
                        state = state
                    ) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                horizontal = 15.dp, vertical = 5.dp
                            ),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(state.notes.filter { it.noteType == NoteType.NOTE.name }) { model ->
                                NoteItem(
                                    model,
                                    onClicked = { noteModel ->
                                        noteModel?.let { safeNoteModel ->
                                            onNoteItemClicked.invoke(safeNoteModel)
                                        }
                                    },
                                    onLongClicked = { noteModel ->
                                        noteModel?.let { safeNoteModel ->
                                            val newModel: NoteModel =
                                                safeNoteModel.copy(isSelected = safeNoteModel.isSelected.not())
                                            onNoteItemLongClicked.invoke(newModel)
                                        }
                                    },
                                )
                            }
                        }
                    }

                } else {
                    Text(
                        text = stringResource(id = R.string.note_no_content),
                        style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center
                    )
                }


            }


        }
    )


}




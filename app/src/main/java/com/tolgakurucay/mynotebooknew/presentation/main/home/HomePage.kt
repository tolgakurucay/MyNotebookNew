package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    val isShowingExitDialog = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotes()
    }


    if (observableState.value.isUserLoggedOut == true) {
        LaunchedEffect(key1 = "LogOut") {
            onLogOutClicked.invoke()
        }
    }

    if (isShowingExitDialog.value) {
        CustomAlertDialog(
            type = AlertDialogType.YES_OR_NO,
            descriptionRes = stringResource(id = R.string.question_you_want_exit),
            onConfirm = { viewModel.logOut() },
            onDismiss = { isShowingExitDialog.value = false })
    }


    HomeContent(
        observableState.value,
        homeNavigations = homeNavigations,
        onLogOutClicked = {
            isShowingExitDialog.value = true
        },
        onNoteItemClicked = onNoteItemClicked,
        onNoteItemLongClicked = {
            viewModel.doSelectableOrNot(it)
        }, onFavoritesClicked = {
            viewModel.addNotesToFavorite(observableState.value.notes.filter { it.isSelected })
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
    onFavoritesClicked: () -> Unit = {}

) {

    BaseScaffold(
        state = state,
        topBar = {
            HomeTopBar(state.isShowingTheMenu) {
                when (it) {
                    HomeTopBarActions.FAVORITE -> {
                        onFavoritesClicked.invoke()
                    }

                    HomeTopBarActions.UPDATE -> {

                    }

                    HomeTopBarActions.DELETE -> {

                    }
                }
            }
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
        content = {
            BaseColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight(), state = state
            ) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        horizontal = 15.dp, vertical = 5.dp
                    ),
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
        }
    )


}




package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tolgakurucay.mynotebooknew.presentation.main.home.NoteItem


@Composable
fun FavoritesPage(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onNoteItemClicked: (NoteModel) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getFavorites()
    }

    DisposableEffect(Unit){
        onDispose {
            viewModel.removeAllSelectedItems()
        }

    }


    FavoritesContent(
        state = state,
        onNoteItemClicked = onNoteItemClicked,
        onNoteItemLongClicked = {
            viewModel.doSelectableOrNot(it)
        },
        actions = { actions ->
            when (actions) {
                FavoritesTopBarActions.DELETE -> {
                    viewModel.deleteSelectedNotes()
                }

                FavoritesTopBarActions.REMOVE_FROM_FAVORITES -> {
                    viewModel.removeFromFavorites()
                }

                FavoritesTopBarActions.BACK -> {
                    onBackPressed.invoke()
                }
            }

        }
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun FavoritesContent(
    state: FavoritesState = FavoritesState(),
    actions: (FavoritesTopBarActions) -> Unit = {},
    onNoteItemLongClicked: (NoteModel) -> Unit = {},
    onNoteItemClicked: (NoteModel) -> Unit = {}
) {

    BaseScaffold(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = { FavoritesTopBar(actions = actions, showingTheToolbar = state.list.any { it.isSelected }) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeContent)
                .padding(it)
        ) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.list.isNotEmpty()) {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(
                            horizontal = 15.dp, vertical = 5.dp
                        ),
                    ) {
                        items(state.list) { model ->
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
                } else {
                    Text(
                        text = stringResource(id = R.string.favorites_no_content),
                        style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}


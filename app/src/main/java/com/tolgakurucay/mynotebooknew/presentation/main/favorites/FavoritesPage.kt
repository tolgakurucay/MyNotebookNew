package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.presentation.main.home.NoteItem


@Composable
fun FavoritesPage(viewModel: FavoritesViewModel = hiltViewModel(), onBackPressed: () -> Unit ) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.getFavorites()
    }


    FavoritesContent(state,onBackPressed)

}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
private fun FavoritesContent(state: FavoritesState = FavoritesState(),onBackPressed: () -> Unit = {}) {

    BaseScaffold(
        state = state,
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
        topBar = { FavoritesTopBar(onBackPressed = onBackPressed) }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeContent)
                .padding(it)
        ) {

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(
                    horizontal = 15.dp, vertical = 5.dp
                ),
            ) {
                items(state.list.filter { it.noteType == NoteType.FAVORITE.name }) { model ->
                    NoteItem(
                        model,
                        onClicked = { noteModel ->
                            noteModel?.let { safeNoteModel ->
                                //onNoteItemClicked.invoke(safeNoteModel)
                            }
                        },
                        onLongClicked = { noteModel ->
                            noteModel?.let { safeNoteModel ->
                                val newModel: NoteModel =
                                    safeNoteModel.copy(isSelected = safeNoteModel.isSelected.not())
                                // onNoteItemLongClicked.invoke(newModel)
                            }
                        },
                    )
                }
            }


        }


    }
}
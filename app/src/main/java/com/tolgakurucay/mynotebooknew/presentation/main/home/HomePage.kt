package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel

@Composable
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit,
    onLogOutClicked: () -> Unit,
    onNoteItemClicked: (NoteModel) -> Unit
) {

    val observableState = viewModel.state.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        viewModel.getNotes()
    }


    if (observableState.isUserLoggedOut == true) {
        LaunchedEffect(key1 = "LogOut") {
            onLogOutClicked.invoke()
        }
    }


    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        HomeContent(
            observableState,
            homeNavigations = homeNavigations,
            onLogOutClicked = {
                viewModel.logOut()
            },
            onNoteItemClicked = onNoteItemClicked
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HomeContent(
    state: HomeState = HomeState(),
    homeNavigations: (HomeNavigations) -> Unit = {},
    onLogOutClicked: () -> Unit = {},
    onNoteItemClicked: (NoteModel) -> Unit = {}

) {

    BaseScaffold(
        state = state,
        topBar = {
            HomeTopBar()
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
            BaseColumn(modifier = Modifier.padding(it), state = state) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(
                        horizontal = 15.dp, vertical = 5.dp
                    ),
                ) {
                    items(state.notes) { model ->
                        NoteItem(model) { noteModel ->
                            noteModel?.let { safeNoteModel ->
                                onNoteItemClicked.invoke(safeNoteModel)
                            }
                        }
                    }
                }

            }
        }
    )


}




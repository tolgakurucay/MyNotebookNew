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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.size15

@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit,
    loggedOut: () -> Unit,
    gotToEditOrView: (NoteModel) -> Unit

    ) {
    viewModel.getNotes()
    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeContent(
            viewModel.state.value,
            homeNavigations = homeNavigations,
            loggedOut = loggedOut,
            logout = { viewModel.logout() },
            goToEditOrView = gotToEditOrView
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun HomeContent(
    state: HomeState = HomeState(),
    homeNavigations: (HomeNavigations) -> Unit = {},
    loggedOut: () -> Unit = {},
    logout: () -> Unit = {},
    goToEditOrView: (NoteModel) -> Unit = {}

) {


    fun observeState() {
        if (state.isUserLoggedOut == true) {
            state.isUserLoggedOut = false
            loggedOut.invoke()
        }

    }

    observeState()

    BaseScaffold(
        state = state,
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar {
                if (it == HomeNavigations.LOGOUT) {
                    logout.invoke()
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
                        size15
                    ),
                ) {
                    items(state.notes) { model ->
                        NoteItem(model) {
                            it?.let {
                                goToEditOrView.invoke(it)
                            }
                        }
                    }
                }

            }
        }
    )


}




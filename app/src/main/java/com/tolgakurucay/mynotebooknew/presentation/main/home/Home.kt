package com.tolgakurucay.mynotebooknew.presentation.main.home

import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    LaunchedEffect(key1 = Unit){
        viewModel.getNotes()
        Log.d("bilgitolga", "Home: getnotes")
    }

    val observableState = viewModel.state.collectAsStateWithLifecycle().value

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        HomeContent(
            observableState,
            homeNavigations = homeNavigations,
            loggedOut = loggedOut,
            logout = {},
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




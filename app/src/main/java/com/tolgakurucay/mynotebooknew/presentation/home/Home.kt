package com.tolgakurucay.mynotebooknew.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.size15

@Preview
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit = {}

) {

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeContent(
            viewModel,
            homeNavigations = homeNavigations
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    homeNavigations: (HomeNavigations) -> Unit
) {

    val notes = listOf<NoteModel>()

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar(
                homeNavigation = homeNavigations,
            )
        },
        content = {
            BaseColumn(modifier = Modifier.padding(it), viewModel = viewModel) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2), contentPadding = PaddingValues(
                        size15
                    )
                ) {
                    items(notes) { model ->

                    }
                }

            }
        }
    )


}

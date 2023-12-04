package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold


@Composable
fun FavoritesPage(viewModel: FavoritesViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    FavoritesContent(state)

}

@Preview
@Composable
private fun FavoritesContent(state: FavoritesState = FavoritesState()) {

    BaseScaffold(
        state = state, modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {


    }
}
package com.tolgakurucay.mynotebooknew.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

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

    Scaffold(
        bottomBar = {
            HomeBottomBar(
                homeNavigation = homeNavigations,
            )
        },
    ) {
        it.calculateTopPadding()
        Text(text = "adsf")
    }


}

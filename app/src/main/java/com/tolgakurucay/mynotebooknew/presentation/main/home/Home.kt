package com.tolgakurucay.mynotebooknew.presentation.main.home

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.theme.size15
import com.tolgakurucay.mynotebooknew.util.showLog

@Preview
@Composable
fun Home(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit = {},
    loggedOut: () -> Unit = {}

) {

    viewModel.addNote(model = NoteModel("Tolgagın defteri","asdasdasdasd",null,0))

    Surface(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeContent(
            viewModel,
            homeNavigations = homeNavigations,
            loggedOut = loggedOut,
        )
    }
}

@Composable
fun HomeContent(
    viewModel: HomeViewModel,
    homeNavigations: (HomeNavigations) -> Unit,
    loggedOut: () -> Unit
) {

    val state = viewModel.state.value

    fun observeState(){
        if(state.isUserLoggedOut == true){
            state.isUserLoggedOut = false
            loggedOut.invoke()
        }

    }

    observeState()




    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            HomeBottomBar{
                if(it == HomeNavigations.LOGOUT){
                    viewModel.logout()
                }
                else{
                    homeNavigations.invoke(it)
                }
            }
        },
        content = {
            BaseColumn(modifier = Modifier.padding(it), viewModel = viewModel) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(count = 2), contentPadding = PaddingValues(
                        size15
                    )
                ) {
                    items(state.notes) { model ->
                        NoteItem(model){
                            showLog(model)
                        }
                    }
                }

            }
        }
    )



}




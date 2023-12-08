package com.tolgakurucay.mynotebooknew.presentation.main.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tolgakurucay.mynotebooknew.R
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonSize
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.presentation.main.home.NoteItem
import com.tolgakurucay.mynotebooknew.util.orZero
import com.tolgakurucay.mynotebooknew.util.setStateFalse
import com.tolgakurucay.mynotebooknew.util.setStateTrue
import com.tolgakurucay.mynotebooknew.util.share
import com.tolgakurucay.mynotebooknew.util.showLog
import com.tolgakurucay.mynotebooknew.util.toDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.ZoneId


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesPage(
    viewModel: FavoritesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onNoteItemClicked: (NoteModel) -> Unit,
    datePickerState: DatePickerState = rememberDatePickerState(),
    timePickerState: TimePickerState = rememberTimePickerState(),
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.removeAllSelectedItems()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getFavorites()
    }


    val isShowDeleteDialog = remember {
        mutableStateOf(false)
    }

    val isShowRemoveFromFavoritesDialog = remember {
        mutableStateOf(false)
    }

    val isShared = remember {
        mutableStateOf(false)
    }

    val isShowTheDatePickerDialog = remember {
        mutableStateOf(false)
    }

    val isShowTheTimePickerDialog = remember {
        mutableStateOf(false)
    }


    if (isShowDeleteDialog.value) {
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.common_information,
            descriptionText = stringResource(
                id = R.string.question_you_want_delete
            ),
            onConfirm = {
                viewModel.deleteSelectedNotes()
            },
            onDismiss = {
                isShowDeleteDialog.setStateFalse()
            }
        )
    }

    if (isShowRemoveFromFavoritesDialog.value) {
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.common_information,
            descriptionText = stringResource(
                id = R.string.question_you_want_remove_from_favorites
            ),
            onConfirm = {
                viewModel.removeFromFavorites()
            },
            onDismiss = {
                isShowRemoveFromFavoritesDialog.setStateFalse()
            }
        )
    }

    if (isShared.value) {
        val model = state.list.find { it.isSelected }
        LocalContext.current.share(model?.title.toString(), model?.description.toString()) {
            isShared.setStateFalse()
        }
    }

    if (isShowTheDatePickerDialog.value) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                    isShowTheDatePickerDialog.setStateFalse()
                }
            },
            sheetState = bottomSheetState
        ) {
            DatePicker(state = datePickerState)
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomButton(
                    ButtonType.CANCEL,
                    horizontalMargin = 4.dp,
                    buttonSize = ButtonSize.WRAP_CONTENT
                ) {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        isShowTheDatePickerDialog.setStateFalse()
                    }

                }
                CustomButton(
                    ButtonType.OK,
                    horizontalMargin = 4.dp,
                    buttonSize = ButtonSize.WRAP_CONTENT
                ) {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        isShowTheDatePickerDialog.setStateFalse()
                        isShowTheTimePickerDialog.setStateTrue()
                    }

                }

            }
        }


    }

    if (isShowTheTimePickerDialog.value) {

        Box(
            modifier = Modifier.clip(MaterialTheme.shapes.medium)
        ) {
            Dialog(onDismissRequest = { isShowTheTimePickerDialog.setStateFalse() }) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp)

                ) {
                    TimePicker(state = timePickerState)
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CustomButton(
                            ButtonType.CANCEL,
                            horizontalMargin = 4.dp,
                            buttonSize = ButtonSize.WRAP_CONTENT
                        ) {

                            isShowTheTimePickerDialog.setStateFalse()
                        }
                        CustomButton(
                            ButtonType.OK,
                            horizontalMargin = 4.dp,
                            buttonSize = ButtonSize.WRAP_CONTENT
                        ) {
                            isShowTheTimePickerDialog.setStateFalse()

                            val selectedDate =
                                datePickerState.selectedDateMillis.orZero() + (timePickerState.minute * 1000).toLong() + (timePickerState.hour * 60 * 1000).toLong()

                            val currentDate = System.currentTimeMillis() + 1000*10
                            state.list.find { it.isSelected }?.let{
                                it.alarmDate = selectedDate
                                viewModel.setAnAlarm(it)
                            }
                            val parsedDate =
                                selectedDate.toDate().toInstant().atZone(ZoneId.systemDefault())
                            showLog(parsedDate)

                        }

                    }

                }
            }
        }
    }




    FavoritesContent(
        state = state,
        onNoteItemClicked = {
            if (state.list.any { it.isSelected }) {
                viewModel.doSelectableOrNot(it.copy(isSelected = it.isSelected.not()))
            } else {
                onNoteItemClicked.invoke(it)
            }
        },
        onNoteItemLongClicked = {
            viewModel.doSelectableOrNot(it)
        },
        actions = { actions ->
            when (actions) {
                is FavoritesTopBarActions.Delete -> {
                    isShowDeleteDialog.setStateTrue()
                }

                is FavoritesTopBarActions.RemoveFromFavorites -> {
                    isShowRemoveFromFavoritesDialog.setStateTrue()
                }

                is FavoritesTopBarActions.Search -> {
                    viewModel.searchNotesByText(actions.searchString)
                }

                is FavoritesTopBarActions.Back -> {
                    onBackPressed.invoke()
                }

                is FavoritesTopBarActions.Share -> {
                    isShared.setStateTrue()
                }

                is FavoritesTopBarActions.SetAnAlarm -> {
                    isShowTheDatePickerDialog.setStateTrue()
                }
            }

        }
    )

}

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
        topBar = {
            FavoritesTopBar(
                actions = actions,
                showingTheToolbar = state.list.any { it.isSelected },
                showItemsForOneAction = state.list.filter { it.isSelected }.size == 1
            )
        }
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


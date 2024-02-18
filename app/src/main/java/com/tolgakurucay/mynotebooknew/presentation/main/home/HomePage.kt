package com.tolgakurucay.mynotebooknew.presentation.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import com.tolgakurucay.mynotebooknew.domain.base.BaseColumn
import com.tolgakurucay.mynotebooknew.domain.base.BaseScaffold
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import com.tolgakurucay.mynotebooknew.presentation.custom.AlertDialogType
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonSize
import com.tolgakurucay.mynotebooknew.presentation.custom.ButtonType
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomAlertDialog
import com.tolgakurucay.mynotebooknew.presentation.custom.CustomButton
import com.tolgakurucay.mynotebooknew.util.isNull
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
fun HomePage(
    viewModel: HomeViewModel = hiltViewModel(),
    homeNavigations: (HomeNavigations) -> Unit,
    onLogOutClicked: () -> Unit,
    onNoteItemClicked: (NoteModel) -> Unit,
    datePickerState: DatePickerState = rememberDatePickerState(),
    timePickerState: TimePickerState = rememberTimePickerState(),
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {

    val observableState = viewModel.state.collectAsStateWithLifecycle()

    val isShowingExitDialog = remember { mutableStateOf(false) }
    val isShared = remember { mutableStateOf(false) }
    val isShowTheDatePickerDialog = remember { mutableStateOf(false) }
    val isShowDeleteDialog = remember { mutableStateOf(false) }
    val isShowTheTimePickerDialog = remember { mutableStateOf(false) }
    val isShowEmptyRightDialog = remember { mutableStateOf(false) }
    val isShowLessRightDialog = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = observableState.value.userRightsToAddNote){
        observableState.value.userRightsToAddNote?.let {safeRights->
            if(safeRights == 0){
                isShowEmptyRightDialog.setStateTrue()
            }
            else if(safeRights < observableState.value.notes.filter { it.isSelected }.size){
                isShowLessRightDialog.setStateTrue()
            }
            else{
                viewModel.addSelectedNotesToCloud(observableState.value.notes)
            }
        }
    }

    if(isShowEmptyRightDialog.value){
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.common_information,
            descriptionText = stringResource(
                id = R.string.desc_empty_right_dialog
            ),
            onConfirm = {
                homeNavigations.invoke(HomeNavigations.PROFILE)
                isShowEmptyRightDialog.setStateFalse()
            },
            onDismiss = {
                isShowEmptyRightDialog.setStateFalse()
            }
        )
    }

    if(isShowLessRightDialog.value){
        CustomAlertDialog(type = AlertDialogType.YES_OR_NO,
            titleRes = R.string.common_information,
            descriptionText = stringResource(
                id = R.string.desc_less_right_dialog
            ),
            onConfirm = {
                homeNavigations.invoke(HomeNavigations.PROFILE)
                isShowLessRightDialog.setStateFalse()
            },
            onDismiss = {
                isShowLessRightDialog.setStateFalse()
            }
        )
    }

    if (isShared.value) {
        val model = observableState.value.notes.find { it.isSelected }
        LocalContext.current.share(model?.title.toString(), model?.description.toString()) {
            isShared.setStateFalse()
        }
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



    LaunchedEffect(key1 = Unit) {
        viewModel.getNotes()
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            viewModel.removeAllSelectedItems()
        }
    }


    if (observableState.value.isUserLoggedOut == true) {
        LaunchedEffect(key1 = "LogOut") {
            onLogOutClicked.invoke()
        }
    }

    if (isShowingExitDialog.value) {
        CustomAlertDialog(
            type = AlertDialogType.YES_OR_NO,
            descriptionText = stringResource(id = R.string.question_you_want_exit),
            onConfirm = { viewModel.logOut() },
            onDismiss = { isShowingExitDialog.value = false })
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

                            // val tenSecond = System.currentTimeMillis() + 10000
                            observableState.value.notes.find { it.isSelected }?.let {
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


    HomeContent(
        observableState.value,
        homeNavigations = homeNavigations,
        onLogOutClicked = {
            isShowingExitDialog.value = true
        },
        onNoteItemClicked = { noteModel ->
            if (observableState.value.isShowingTheMenu.not()) {
                onNoteItemClicked.invoke(noteModel)
            } else {
                viewModel.doSelectableOrNot(noteModel.copy(isSelected = noteModel.isSelected.not()))
            }

        },
        onNoteItemLongClicked = { viewModel.doSelectableOrNot(it) },
        onTopBarActionsClicked = {
            when (it) {
                HomeTopBarActions.Delete -> isShowDeleteDialog.setStateTrue()
                HomeTopBarActions.Favorite -> viewModel.addNotesToFavorite(observableState.value.notes.filter { it.isSelected })
                is HomeTopBarActions.Search -> viewModel.searchNotesByText(it.searchString)
                HomeTopBarActions.SetAnAlarm -> {}
                HomeTopBarActions.Share -> isShared.setStateTrue()
                HomeTopBarActions.Cloud -> {
                    viewModel.getUserRights()
                }
            }
        },
        onSnackBarUndoClicked = {
            observableState.value.notes.find { it.isSelected }?.let { safeModel ->
                safeModel.alarmDate = null
                viewModel.cancelTheAlarm(safeModel)
            }
        },
        onSnackBarDismissed = {
            viewModel.dismissSnackBar()
        }
    )
}

@Preview
@Composable
fun HomeContent(
    state: HomeState = HomeState(),
    homeNavigations: (HomeNavigations) -> Unit = {},
    onLogOutClicked: () -> Unit = {},
    onNoteItemClicked: (NoteModel) -> Unit = {},
    onNoteItemLongClicked: (NoteModel) -> Unit = {},
    onTopBarActionsClicked: (HomeTopBarActions) -> Unit = {},
    onSnackBarDismissed: () -> Unit = {},
    onSnackBarUndoClicked: () -> Unit = {},

    ) {

    val snackbarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val snackBarMessage = stringResource(id = R.string.question_you_want_undo)
    val snackBarButtonMessage = stringResource(id = R.string.common_undo)



    if (state.isSnackBarShow) {
        LaunchedEffect(Unit) {
            coroutineScope.launch {
                snackbarState.showSnackbar(
                    message = snackBarMessage,
                    actionLabel = snackBarButtonMessage,
                    duration = SnackbarDuration.Long,
                    withDismissAction = true
                ).also {
                    when (it.ordinal) {
                        0 -> {
                            onSnackBarDismissed.invoke()
                        }

                        1 -> {
                            onSnackBarUndoClicked.invoke()
                        }
                    }

                }

            }
        }
    }

    BaseScaffold(
        state = state,
        topBar = {
            HomeTopBar(
                showingTheToolbar = state.isShowingTheMenu,
                actions = onTopBarActionsClicked,
                showItemsForOneAction = state.notes.filter { it.isSelected }.size == 1
            )
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
        snackBarHost = {
            SnackbarHost(hostState = snackbarState)
        },
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (state.notes.any { it.noteType == NoteType.NOTE.name }) {
                    BaseColumn(
                        state = state
                    ) {
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Fixed(2),
                            contentPadding = PaddingValues(
                                horizontal = 15.dp, vertical = 5.dp
                            ),
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(state.notes.filter { it.noteType == NoteType.NOTE.name }) { model ->
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
                    }

                } else {
                    Text(
                        text = stringResource(id = R.string.note_no_content),
                        style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center
                    )
                }


            }


        }
    )


}




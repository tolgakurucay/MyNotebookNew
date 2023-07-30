package com.tolgakurucay.mynotebooknew.domain.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {
    var myNotebookException: MutableStateFlow<BaseException?> = MutableStateFlow(null)
    val isShowLoading: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    fun showWaitingDialog(isShow: Boolean){
        isShowLoading.value = isShow
    }

}
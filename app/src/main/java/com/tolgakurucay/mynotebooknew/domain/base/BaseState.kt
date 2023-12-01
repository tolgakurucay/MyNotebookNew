package com.tolgakurucay.mynotebooknew.domain.base

import kotlinx.coroutines.flow.MutableStateFlow


open class BaseState {
    var myNotebookException: MutableStateFlow<BaseException?> = MutableStateFlow(null)
    var isShowLoading = MutableStateFlow(false)
}
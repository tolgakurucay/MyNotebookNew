package com.tolgakurucay.mynotebooknew.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.mynotebooknew.base.throwable.BaseThrowable

open class BaseViewModel : ViewModel() {
    val myNotebookError: MutableLiveData<BaseThrowable> = MutableLiveData()
    val isShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun showWaitingDialog(isShow: Boolean) =
        isShowLoading.postValue(isShow)
}
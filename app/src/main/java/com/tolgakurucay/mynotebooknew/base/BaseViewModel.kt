package com.tolgakurucay.mynotebooknew.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.mynotebooknew.base.throwable.BaseThrowable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(): ViewModel() {
    val myNotebookError: MutableLiveData<BaseThrowable> = MutableLiveData()
    val isShowLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun showWaitingDialog(isShow: Boolean) =
        isShowLoading.postValue(isShow)
}
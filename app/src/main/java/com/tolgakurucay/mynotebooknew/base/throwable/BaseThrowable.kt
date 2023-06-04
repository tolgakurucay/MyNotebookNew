package com.tolgakurucay.mynotebooknew.base.throwable

import com.tolgakurucay.mynotebooknew.base.MessageModel


open class BaseThrowable : Throwable {

    var showMessage: Boolean = true
        private set
    var stopLoading: Boolean = true
        private set
    var finishScreen: Boolean = false
        private set
    var uiMessage: Any
        get() {
            isHandled = true
            return if (field is List<*> && (field as List<*>).isNullOrEmpty() || "" == field) noneEmptyMessage else field
        }
        private set

    var responseCode: Int? = null

//    var retryListener: ((dialog: IDialogInterface) -> Unit)? = null
//        private set

    var isHandled = false
        private set

    protected open var noneEmptyMessage = "None empty message"

    constructor(
        cause: Throwable,
        finishScreen: Boolean = true,
        uiMessage: List<MessageModel>? = null
    ) : super(cause) {
        this.showMessage = true
        this.stopLoading = true
        this.finishScreen = finishScreen
        (uiMessage ?: arrayListOf()).also { this.uiMessage = it }
//        this.retryListener = retryListener
    }

    constructor(
        finishScreen: Boolean = true,
        uiMessage: List<MessageModel>? = null,
        showMessage: Boolean = true,
        responseCode: Int? = null
    ) : super(uiMessage.toString()) {
        this.showMessage = showMessage
        this.stopLoading = true
        this.finishScreen = finishScreen
        this.responseCode = responseCode
        (uiMessage ?: arrayListOf()).also { this.uiMessage = it }
//        this.retryListener = retryListener
    }
}
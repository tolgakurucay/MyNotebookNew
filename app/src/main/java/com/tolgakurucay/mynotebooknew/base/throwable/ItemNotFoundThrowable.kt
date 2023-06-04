package com.tolgakurucay.mynotebooknew.base.throwable

import com.tolgakurucay.mynotebooknew.base.MessageModel

class ItemNotFoundThrowable(uiMessage: List<MessageModel>? = null) :
    BaseThrowable(false, uiMessage, false) {
    override var noneEmptyMessage = "errorSuggestionsNoteFound"
}
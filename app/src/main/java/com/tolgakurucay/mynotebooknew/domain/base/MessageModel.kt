package com.tolgakurucay.mynotebooknew.domain.base

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageModel(
    val type: MessageType? = MessageType.PLAIN,
    val message: String?
) : Parcelable {
    override fun toString(): String {
        return when (type) {
            MessageType.PLAIN -> message ?: ""
//            MessageType.HTML -> message?.makeHtml().toString()
            else -> message ?: ""
        }
    }
}
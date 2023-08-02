package com.tolgakurucay.mynotebooknew.domain.model.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel(
    var title:String?,
    var description:String?,
    var byteArray: ByteArray?,
    var date: Long?,
    var id:Int?=null
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoteModel

        if (byteArray != null) {
            if (other.byteArray == null) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
        } else if (other.byteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        return byteArray?.contentHashCode() ?: 0
    }
}

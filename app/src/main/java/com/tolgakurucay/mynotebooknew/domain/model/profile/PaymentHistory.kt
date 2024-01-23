package com.tolgakurucay.mynotebooknew.domain.model.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class PaymentHistory(
    val date: Timestamp,
    val orderId: String,
    val packageId: String,
    val packageName: String,
    val personUID: String,
    val price: String,
    val purchaseToken: String
): Parcelable

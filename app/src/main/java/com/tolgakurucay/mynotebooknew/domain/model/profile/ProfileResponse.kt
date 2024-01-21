package com.tolgakurucay.mynotebooknew.domain.model.profile

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileResponse(
    val name: String? = null,
    val surname: String? = null,
    val photo: String? = null,
    val phoneNumber: String? = null,
    val mail: String? = null,

) : Parcelable

package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfileInfo(): Flow<ProfileResponse>
    suspend fun getRights()
    suspend fun getPaymentHistory()
    suspend fun deleteAccount()


}
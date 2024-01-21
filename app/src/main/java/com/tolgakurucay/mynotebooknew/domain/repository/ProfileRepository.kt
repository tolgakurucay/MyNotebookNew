package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfileInfo(): Flow<ProfileResponse>
    suspend fun getRights(): Flow<Int>
    suspend fun getPaymentHistory()
    suspend fun deleteAccount()
    suspend fun updateProfileInformation(request: ProfileResponse): Flow<Boolean>


}
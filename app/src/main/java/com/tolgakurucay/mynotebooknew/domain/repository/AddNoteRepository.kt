package com.tolgakurucay.mynotebooknew.domain.repository

import com.tolgakurucay.mynotebooknew.domain.base.Result
import kotlinx.coroutines.flow.Flow

interface AddNoteRepository {

    suspend fun hasCloudRight() : Flow<Result<Boolean>>


}
package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.model.Result
import com.tolgakurucay.mynotebooknew.domain.repository.AddNoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AddNoteRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AddNoteRepository {
    override suspend fun hasCloudRight(): Flow<Result<Boolean>> = flow {
        auth.currentUser?.let {
            try {
                val document = firestore.collection("Right").document(it.uid).get().await()
                val cloudRightSize = document.getDouble("right")
                cloudRightSize?.let { size ->
                    if (size > 0) emit(Result.success(false)) else emit(Result.success(true))
                } ?: kotlin.run {
                    emit(Result.success(false))
                }
            } catch (ex: Exception) {
                emit(Result.error(exception = BaseException(cause = ex)))
            }


        }
    }
}
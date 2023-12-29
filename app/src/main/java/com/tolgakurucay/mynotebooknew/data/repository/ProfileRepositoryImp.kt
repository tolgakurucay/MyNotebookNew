package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.domain.model.profile.ProfileResponse
import com.tolgakurucay.mynotebooknew.domain.repository.ProfileRepository
import com.tolgakurucay.mynotebooknew.util.showLog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : ProfileRepository {

    override suspend fun getProfileInfo(): Flow<ProfileResponse>  = flow{
        firestore.collection("Users").document(auth.currentUser?.uid.toString()).get().await()
            ?.let {
                if (it.exists()) {
                    it.toObject(ProfileResponse::class.java)?.let {
                        showLog("ProfileResponse : $it")
                        emit(it)
                    }
                } else {

                }
            }


    }

    override suspend fun getRights() {
    }

    override suspend fun getPaymentHistory() {
    }

    override suspend fun deleteAccount() {
    }
}
package com.tolgakurucay.mynotebooknew.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.repository.AddNoteRepositoryImp
import com.tolgakurucay.mynotebooknew.data.repository.AuthRepositoryImpl
import com.tolgakurucay.mynotebooknew.data.repository.HomeRepositoryImp
import com.tolgakurucay.mynotebooknew.domain.repository.AddNoteRepository
import com.tolgakurucay.mynotebooknew.domain.repository.AuthRepository
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository =
        AuthRepositoryImpl(auth, firestore)

    @Singleton
    @Provides
    fun provideAddNoteRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AddNoteRepository =
        AddNoteRepositoryImp(auth, firestore)

    @Singleton
    @Provides
    fun provideHomeRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): HomeRepository =
        HomeRepositoryImp(auth, firestore)




}
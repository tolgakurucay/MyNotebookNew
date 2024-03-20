package com.tolgakurucay.mynotebooknew.data.repository

import com.tolgakurucay.mynotebooknew.data.datasources.home_data_source.HomeDataSource
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getAllNotesFromRemote(): Flow<List<NoteModel>> = homeDataSource.getAllNotesFromRemote()
    override suspend fun getAllNotesFromLocale(): Flow<List<NoteModel>> = homeDataSource.getAllNotesFromLocale()
    override suspend fun updateNoteFromLocale(model: NoteModel): Int? = homeDataSource.updateNoteFromLocale(model)
    override suspend fun updateNotesFromLocale(list: List<NoteModel>): Int? = homeDataSource.updateNotesFromLocale(list)
    override suspend fun updateNoteFromRemote(model: NoteModel): Flow<Boolean> = homeDataSource.updateNoteFromRemote(model)
    override suspend fun addNoteToLocale(model: NoteModel): Long = homeDataSource.addNoteToLocale(model)
    override suspend fun addNoteToRemote(model: NoteModel): Flow<Boolean> = homeDataSource.addNoteToRemote(model)
    override suspend fun addNotesToRemote(list: List<NoteModel>): Flow<Boolean> = homeDataSource.addNotesToRemote(list)
    override suspend fun deleteNoteFromLocale(model: NoteModel): Int = homeDataSource.deleteNoteFromLocale(model)
    override suspend fun deleteNoteFromRemote(model: NoteModel): Flow<Boolean> = homeDataSource.deleteNoteFromRemote(model)
    override suspend fun deleteNotesFromLocale(list: List<NoteModel>): Int = homeDataSource.deleteNotesFromLocale(list)
    override suspend fun deleteNotesFromRemote(list: List<NoteModel>): Flow<Boolean> = homeDataSource.deleteNotesFromRemote(list)
    override suspend fun searchNoteByAll(searchText: String): Flow<List<NoteModel>> = homeDataSource.searchNoteByAll(searchText)
    override suspend fun getUserRights(): Flow<Int> = homeDataSource.getUserRights()
    override suspend fun decreaseUserRights(newRight: Int): Flow<Boolean> = homeDataSource.decreaseUserRights(newRight)
}
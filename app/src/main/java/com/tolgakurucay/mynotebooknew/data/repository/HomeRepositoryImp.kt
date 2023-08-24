package com.tolgakurucay.mynotebooknew.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tolgakurucay.mynotebooknew.data.database.NoteDao
import com.tolgakurucay.mynotebooknew.domain.base.BaseException
import com.tolgakurucay.mynotebooknew.domain.model.Result
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.repository.HomeRepository
import com.tolgakurucay.mynotebooknew.util.flowService
import com.tolgakurucay.mynotebooknew.util.resultService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val noteDao: NoteDao
) : HomeRepository {
    override suspend fun getAllNotesFromRemote(): Result<List<NoteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNotesFromLocale(): Result<List<NoteModel>> {
        return try {
            Result.success(noteDao.getAllNotes())
        } catch (ex: Exception) {
            Result.error(exception = BaseException(cause = ex))
        }
    }

    override suspend fun getAllNotesFromLocaleAsc(): Result<List<NoteModel>> {
        return try {
            Result.success(noteDao.getAllNotesByAsc())
        } catch (ex: Exception) {
            Result.error(exception = BaseException(cause = ex))
        }
    }

    override suspend fun getAllNotesFromRemoteAsc(): Result<List<NoteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNotesFromLocaleDesc(): Result<List<NoteModel>> {
        return try {
            Result.success(noteDao.getAllNotesByDesc())
        } catch (ex: Exception) {
            Result.error(exception = BaseException(cause = ex))
        }
    }

    override suspend fun getAllNotesFromRemoteDesc(): Result<List<NoteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNoteByTitleFromLocale(title: String): Flow<Result<NoteModel>> =
        flowService(noteDao.searchNoteByTitle(title))


    override suspend fun searchNoteByTitleFromRemote(title: String): Flow<Result<NoteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNotesByTitleFromLocale(title: String): Flow<Result<List<NoteModel>>> =
        flowService(noteDao.searchNotesByTitle(title))

    override suspend fun searchNotesByTitleFromRemote(title: String): Flow<Result<List<NoteModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNoteByDescFromLocale(description: String): Flow<Result<NoteModel>> =
        flowService(noteDao.searchNoteByDescription(description))

    override suspend fun searchNoteByDescFromRemote(description: String): Flow<Result<NoteModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchNotesByDescFromLocale(description: String): Flow<Result<List<NoteModel>>> =
        flowService(noteDao.searchNotesByDescription(description))

    override suspend fun searchNotesByDescFromRemote(description: String): Flow<Result<List<NoteModel>>> =
        flow {
            TODO("Not yet implemented")
        }

    override suspend fun deleteNoteFromLocale(model: NoteModel): Result<Boolean> {
        return try {
            noteDao.deleteNote(model)
            Result.success(true)
        } catch (ex: Exception) {
            Result.error(BaseException(cause = ex))
        }
    }

    override suspend fun deleteNoteFromRemote(model: NoteModel): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNotesFromLocale(): Result<Boolean> {
        return try {
            noteDao.deleteAllNotes()
            Result.success(true)
        } catch (ex: Exception) {
            Result.error(BaseException(cause = ex))
        }
    }

    override suspend fun deleteAllNotesFromRemote(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun updateNoteFromLocale(model: NoteModel): Result<Boolean> {
        return try {
            noteDao.updateNote(model)
            Result.success(true)
        } catch (ex: Exception) {
            Result.error(BaseException(cause = ex))
        }
    }

    override suspend fun updateNoteFromRemote(model: NoteModel): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun addNoteToLocale(model: NoteModel): Result<Boolean> =
        resultService { noteDao.addNote(model) }


    override suspend fun addNoteToRemote(model: NoteModel): Result<Boolean> {
        TODO("Not yet implemented")
    }


}
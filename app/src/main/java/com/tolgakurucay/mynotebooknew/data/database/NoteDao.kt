package com.tolgakurucay.mynotebooknew.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteModel
import com.tolgakurucay.mynotebooknew.domain.model.main.NoteType
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteModel: NoteModel): Long

    @Query("SELECT * FROM NoteTable WHERE title LIKE '%' || :searchText || '%' OR description LIKE '%' || :searchText || '%'")
    fun searchNoteByAll(searchText: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM NoteTable WHERE title LIKE :title LIMIT 1")
    fun searchNoteByTitle(title: String): Flow<NoteModel>

    @Query("SELECT * FROM NoteTable WHERE title LIKE :title")
    fun searchNotesByTitle(title: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM NoteTable WHERE description LIKE :description LIMIT 1")
    fun searchNoteByDescription(description: String): Flow<NoteModel>

    @Query("SELECT * FROM NoteTable WHERE description LIKE :description")
    fun searchNotesByDescription(description: String): Flow<List<NoteModel>>

    @Query("SELECT * FROM NoteTable ORDER BY date ASC")
    fun getAllNotesByAsc(): Flow<List<NoteModel>>

    @Query("SELECT * FROM NoteTable ORDER BY date DESC")
    fun getAllNotesByDesc(): Flow<List<NoteModel>>

    @Update
    suspend fun updateNote(noteModel: NoteModel): Int?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(items: List<NoteModel>) : Int?

    @Query("Select * from NoteTable")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Delete
    suspend fun deleteNote(noteModel: NoteModel): Int

    @Delete
    suspend fun deleteNotes(list: List<NoteModel>): Int

    @Query("DELETE FROM NoteTable")
    suspend fun deleteAllNotes()

}
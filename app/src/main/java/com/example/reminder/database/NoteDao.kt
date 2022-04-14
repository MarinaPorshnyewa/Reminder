package com.example.reminder.database

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Query("select * from NoteEntity")
    suspend fun getAllList(): List<NoteEntity>

    @Delete()
    suspend fun deleteNote(note: NoteEntity)

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Delete
    suspend fun deleteAll(list: List<NoteEntity>)
}
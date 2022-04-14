package com.example.reminder.repository

import com.example.reminder.database.NoteDao
import com.example.reminder.database.NoteEntity
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDao: NoteDao){

    //private val noteDao = NoteDataBase.dataBase.noteDao()

    suspend fun saveNote(note: NoteEntity) {

        noteDao.insertNote(note)
    }

    suspend fun getList() = noteDao.getAllList()

    suspend fun deleteN(note: NoteEntity) = noteDao.deleteNote(note)

    suspend fun updateN(note: NoteEntity) = noteDao.updateNote(note)

    suspend fun deleteMyNotes(list: List<NoteEntity>) = noteDao.deleteAll(list)
}
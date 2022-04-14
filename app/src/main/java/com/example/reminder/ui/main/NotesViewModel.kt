package com.example.reminder.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reminder.database.NoteEntity
import com.example.reminder.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNoteRepository: NoteRepository,
) : ViewModel() {


    val dataNote = MutableLiveData<ArrayList<NoteEntity>>()

    //private val getNoteRepository = NoteRepository()

    val myNote = MutableLiveData<NoteEntity>()

    lateinit var showProgressBar: (isShow: Boolean) -> Unit

    lateinit var onItemClick: () -> Unit

    fun saveNote(srcPicture: String, header: String, text: String) {

        viewModelScope.launch {
            showProgressBar(true)
            getNoteRepository.saveNote(NoteEntity(0, srcPicture, header, text))

            loadList()
            showProgressBar(false)
        }
    }


    fun loadList() {

        viewModelScope.launch {
            showProgressBar(true)
            dataNote.value = getNoteRepository.getList() as ArrayList
            showProgressBar(false)
        }

    }

    fun clearAll() {
        viewModelScope.launch {
            getNoteRepository.deleteMyNotes(dataNote.value!!)
            loadList()
        }

    }

    fun clear() {
        myNote.value = NoteEntity(0, "", "", "")
    }

    fun edit(note: NoteEntity) {

        val a = note.header
        val b = note.noteText
        val c = note.key
        val d = note.imageViewSrc
        myNote.value = NoteEntity(c, d, a, b)

    }


    fun edit2(note: NoteEntity) {

        viewModelScope.launch {

            note.header = myNote.value!!.header
            note.noteText = myNote.value!!.noteText
            note.imageViewSrc = myNote.value!!.imageViewSrc

            getNoteRepository.updateN(note)
            loadList()
        }

    }

    fun delete(note: NoteEntity) {

        viewModelScope.launch {

            getNoteRepository.deleteN(note)

            loadList()
        }

    }
}
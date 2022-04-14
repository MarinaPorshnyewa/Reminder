package com.example.reminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(

    @PrimaryKey(autoGenerate = true)
    var key: Long,

    @ColumnInfo(name = "image")
    var imageViewSrc: String,

    @ColumnInfo(name = "header")
    var header: String,

    @ColumnInfo(name = "text")
    var noteText: String

)
package com.example.reminder.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reminder.R
import com.example.reminder.database.NoteDataBase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //NoteDataBase.initDataBase(this)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, NotesFragment())
            .commit()
    }
}
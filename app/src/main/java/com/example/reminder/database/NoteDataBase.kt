package com.example.reminder.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteDataBase {


    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext app: Context) = Room
        .databaseBuilder(app, AppDatabase::class.java, "database-name")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()


    @Singleton
    @Provides
    fun getNoteDao(@ApplicationContext app: Context):NoteDao = Room
        .databaseBuilder(app, AppDatabase::class.java, "database-name")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build().noteDao()


    /*fun initDataBase(context: Context) {
        if (!this::dataBase.isInitialized) {
            dataBase = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }*/
}
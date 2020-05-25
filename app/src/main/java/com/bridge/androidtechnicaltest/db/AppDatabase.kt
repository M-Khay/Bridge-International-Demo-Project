package com.bridge.androidtechnicaltest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pupil::class,NewPupil::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val pupilDao: PupilDao
}
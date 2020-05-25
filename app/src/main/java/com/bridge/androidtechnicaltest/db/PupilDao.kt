package com.bridge.androidtechnicaltest.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Single

@Dao
interface PupilDao  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pupilList : List<Pupil>)

    @Query("DELETE FROM Pupils")
    suspend fun deleteAll()

    @Query("SELECT * FROM Pupils ORDER BY id ASC")
    fun getPupils() : LiveData<List<Pupil>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewPupil(newPupil: NewPupil)

    @Query("SELECT * FROM NewPupils ORDER BY name ASC")
    fun getNewPupilsList() : List<NewPupil>

    @Delete
    suspend fun removeNewPupil(newPupil: NewPupil)
}



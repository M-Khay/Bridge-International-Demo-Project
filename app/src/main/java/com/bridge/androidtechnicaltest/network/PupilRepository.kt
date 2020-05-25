package com.bridge.androidtechnicaltest.network

import androidx.lifecycle.LiveData
import com.bridge.androidtechnicaltest.db.NewPupil
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList

interface PupilRepository {
    suspend fun fetchPupils(page: Int): PupilList
    fun getLastFetchedPupils(): LiveData<List<Pupil>>

    suspend fun addNewPupil(newPupil: NewPupil): Pupil?
    suspend fun syncNewPupil(newPupil: NewPupil): Pupil?
    suspend fun getNewPupilList(): List<NewPupil>
    suspend fun removeNewPupil(newPupil: NewPupil)
}

package com.bridge.androidtechnicaltest.network

import androidx.lifecycle.LiveData
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.NewPupil
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import com.yourself.searchyourcityweather.utils.NetworkConnectivity


class PupilRepositoryImpl(val database: AppDatabase, val pupilApi: PupilApi) : PupilRepository {

    override suspend fun fetchPupils(page: Int): PupilList {

        var result = PupilList(emptyList<Pupil>(), 0)
        if (NetworkConnectivity.isNetworkConnected) {
            val apiResponse = pupilApi.getPupils(page)
            result = apiResponse

            // Delete existing data from Table and save the new fetched result.
            if (page == 1)
                database.pupilDao.deleteAll()
            database.pupilDao.insert(result.items)
        }
        return result
    }

    override fun getLastFetchedPupils(): LiveData<List<Pupil>> {
        return database.pupilDao.getPupils()
    }

    override suspend fun addNewPupil(newPupil: NewPupil): Pupil? {
        var result: Pupil? = null
        if (NetworkConnectivity.isNetworkConnected) {
            result = pupilApi.AddPupil("application/json", newPupil)
        } else {
            database.pupilDao.insertNewPupil(newPupil)
        }
        return result
    }

    override suspend fun syncNewPupil(newPupil: NewPupil): Pupil? {
        return pupilApi.AddPupil("application/json", newPupil)
    }


    override suspend fun getNewPupilList(): List<NewPupil> {
        return database.pupilDao.getNewPupilsList()
    }


    override suspend fun removeNewPupil(newPupil: NewPupil) {
        database.pupilDao.removeNewPupil(newPupil)
    }


}
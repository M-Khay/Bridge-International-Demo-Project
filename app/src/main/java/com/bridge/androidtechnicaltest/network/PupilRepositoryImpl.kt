package com.bridge.androidtechnicaltest.db

import androidx.lifecycle.LiveData
import com.bridge.androidtechnicaltest.network.PupilApi
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

    override suspend fun addNewPupil(newPupil: NewPupil) {
        if (NetworkConnectivity.isNetworkConnected) {
            pupilApi.AddPupil("application/json", newPupil)
        } else {
            database.pupilDao.insertNewPupil(newPupil)
        }
    }

    override suspend fun getNewPupilList(): List<NewPupil> {
        TODO("Not yet implemented")
    }


    override fun getLastFetchedPupils(): LiveData<List<Pupil>> {
        return database.pupilDao.getPupils()
    }


}
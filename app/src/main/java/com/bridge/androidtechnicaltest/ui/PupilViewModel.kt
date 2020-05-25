package com.bridge.androidtechnicaltest.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bridge.androidtechnicaltest.db.NewPupil
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PupilViewModel(private val repository: PupilRepository) : ViewModel() {

    private val TAG = PupilViewModel::class.java.name

    private var lastPageFetched: Int = 0

    val newPupilSyncState = MutableLiveData<ApiResult<Any>>()
    val addNewPupilState = MutableLiveData<Boolean>()

    val _pupilsListState = MutableLiveData<ApiResult<List<Pupil>>>()
    val pupilList: LiveData<List<Pupil>>
        get() = repository.getLastFetchedPupils()


    fun retryGetPupilList() {
        getPupilList(lastPageFetched)
    }

    fun getPupilList(page: Int) {
        if (lastPageFetched > page) {
            return
        }
        _pupilsListState.value =
                Loading(false)
        lastPageFetched = page
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.fetchPupils(page)
                withContext(Dispatchers.Main) {
                    if (result != null) {
                        _pupilsListState.value =
                                Success(result.items, result.totalPages, false)
                    } else {
                        _pupilsListState.value =
                                Error(null, false)
                    }
                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                withContext(Dispatchers.Main) {
                    _pupilsListState.value =
                            Error(exception, false)
                }
            }
        }
    }

    fun addNewPupil(newPupil: NewPupil) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.addNewPupil(newPupil)
                withContext(Dispatchers.Main) {
                    addNewPupilState.value = true
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Error from API ${exception.localizedMessage}")
                    addNewPupilState.value = false
                }
            }

        }
    }

    fun syncNewPupilList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var newPupilList = mutableListOf<NewPupil>()
                withContext(Dispatchers.IO) {
                    newPupilList = repository.getNewPupilList().toMutableList()
                    for (newPupil in newPupilList) {
                        val result = repository.syncNewPupil(newPupil)
                        if (result != null) {
                            repository.removeNewPupil(newPupil)
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "Success from API")
                    newPupilSyncState.value =
                            Success("Success", 0, false)

                }
            } catch (exception: Exception) {
                Log.d(TAG, "Error from API ${exception.localizedMessage}")
                newPupilSyncState.value =
                        Error(exception, false)
            }

        }
    }

}
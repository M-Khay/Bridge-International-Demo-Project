package com.bridge.androidtechnicaltest.network

import com.bridge.androidtechnicaltest.db.NewPupil
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.db.PupilList
import retrofit2.http.*

interface PupilApi {
    @GET("pupils")
    suspend fun getPupils(@Query("page") page: Int = 1): PupilList


    @POST("pupils")
    suspend fun AddPupil(
            @Header("Content-Type") contentType : String,
            @Body pupilViewModel: NewPupil ) : Pupil



}
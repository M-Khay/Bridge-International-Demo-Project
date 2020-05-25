package com.bridge.androidtechnicaltest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewPupils")
data class NewPupil(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 13,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "country")
        val country: String,

        @ColumnInfo(name = "image", defaultValue = "https://screencast.com/t/1KcHuX8R0w")
        val image: String = "https://screencast.com/t/1KcHuX8R0w",

        @ColumnInfo(name = "latitude", defaultValue = "0")
        val latitude: Double = 0.00,

        @ColumnInfo(name = "longitude", defaultValue = "0")
        val longitude: Double = 0.00
)
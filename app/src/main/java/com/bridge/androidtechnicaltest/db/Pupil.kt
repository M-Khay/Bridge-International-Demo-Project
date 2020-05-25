package com.bridge.androidtechnicaltest.db

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bridge.androidtechnicaltest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@Entity(tableName = "Pupils")
data class Pupil(
        @PrimaryKey(autoGenerate = true)
        val id: Int,

        @ColumnInfo(name = "pupil_id")
        val pupilId: Long,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "country")
        val country: String?,

        @ColumnInfo(name = "image")
        val image: String,

        @ColumnInfo(name = "latitude")
        val latitude: Double,

        @ColumnInfo(name = "longitude")
        val longitude: Double
) {
        companion object {
                @JvmStatic
                @BindingAdapter("image")
                fun loadImage(imageView: ImageView, poster: String) {
                        Glide.with(imageView.context)
                                .setDefaultRequestOptions(
                                        RequestOptions()
                                                .circleCrop()
                                ).load(poster)
                                .placeholder(R.mipmap.ic_image_placeholder_round)
                                .into(imageView)
                }
        }
}

class PupilList(
        val items: List<Pupil>,
        val totalPages: Int
)
package com.bridge.androidtechnicaltest.ui.rv

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bridge.androidtechnicaltest.databinding.PupilListItemBinding
import com.bridge.androidtechnicaltest.db.Pupil

class PupilListViewHolder constructor(pupilListItemView: View, private val pupilListItemBinding: PupilListItemBinding) :
        RecyclerView.ViewHolder(pupilListItemView) {

    fun setData(pupil: Pupil) {
        pupilListItemBinding.pupilDetails = pupil
    }

}
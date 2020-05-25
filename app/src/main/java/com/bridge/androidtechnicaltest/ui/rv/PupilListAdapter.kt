package com.bridge.androidtechnicaltest.ui.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bridge.androidtechnicaltest.databinding.PupilListItemBinding
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.ui.PupilViewModel

class PupilListAdapter() : RecyclerView.Adapter<PupilListViewHolder>() {
    private var pupilList = mutableListOf<Pupil>()
    private var totalPages: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PupilListViewHolder {
        val teamListItemBinding =
                PupilListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PupilListViewHolder(teamListItemBinding.root, teamListItemBinding)
    }

    override fun onBindViewHolder(holder: PupilListViewHolder, position: Int) {
        holder.setData(pupilList[position])

        // To Implement ClickListener
        holder.itemView.setOnClickListener {
//            pupilListViewModel.setSelectedMovie(movieList[position])
        }


    }

    override fun getItemCount(): Int {
        return pupilList.size
    }

    fun updateTeamList(pupilList: List<Pupil>) {
        if(this.pupilList.size>pupilList.size) {
            this.pupilList = pupilList.toMutableList()
            notifyDataSetChanged()
        }else{
            this.pupilList.addAll(this.pupilList.size, pupilList.subList(this.pupilList.size, pupilList.size).toMutableList())
            notifyItemRangeInserted(this.pupilList.size , pupilList.size)
        }
    }

    fun setTotalPages(total: Int) {
        totalPages = total
    }

    fun getTotalPages(): Int {
        return totalPages
    }

    fun clearPupilList() {
        this.pupilList.clear()
        notifyDataSetChanged()
    }


}
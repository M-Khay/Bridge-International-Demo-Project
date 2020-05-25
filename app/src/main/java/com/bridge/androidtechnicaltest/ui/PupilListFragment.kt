package com.bridge.androidtechnicaltest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.network.ApiResult
import com.bridge.androidtechnicaltest.network.Error
import com.bridge.androidtechnicaltest.network.Loading
import com.bridge.androidtechnicaltest.network.Success
import com.bridge.androidtechnicaltest.ui.rv.EndlessRecyclerViewScrollListener
import com.bridge.androidtechnicaltest.ui.rv.PupilListAdapter
import kotlinx.android.synthetic.main.fragment_pupillist.*
import org.koin.android.viewmodel.ext.android.viewModel

class PupilListFragment : Fragment() {

    private lateinit var onScrollListener: EndlessRecyclerViewScrollListener
    private lateinit var changeFragmentListener: ChangeFragmentListener
    private val pupilViewModel by viewModel<PupilViewModel>()
    private lateinit var adapter: PupilListAdapter

    interface ChangeFragmentListener {
        fun changeFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChangeFragmentListener) {
            changeFragmentListener = context
        } else {
            throw ClassCastException("The containing activity need to Implement ChangeFragmentListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pupillist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        add_pupil.setOnClickListener {
            changeFragmentListener.changeFragment()
        }
        pupilViewModel.getPupilList(1)
        pupilViewModel._pupilsListState.observe(this.viewLifecycleOwner, apiResultObserver)
        pupilViewModel.pupilList.observe(this.viewLifecycleOwner, dbPupilListObserver)

        adapter = PupilListAdapter()
        pupil_list.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@PupilListFragment.adapter
        }

        pupil_list.addItemDecoration(
                DividerItemDecoration(
                        pupil_list.context,
                        DividerItemDecoration.VERTICAL
                )
        )
        onScrollListener = object :
                EndlessRecyclerViewScrollListener(pupil_list.layoutManager as LinearLayoutManager?) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                // Triggered only when new data needs to be appended to the list
                searchMorePupils(page)
            }
        }
        pupil_list.addOnScrollListener(onScrollListener)
    }


    private val apiResultObserver = Observer<ApiResult<List<Pupil>>> { state ->
        when (state) {
            is Success<List<Pupil>> -> {
                loading_content.visibility = View.GONE
                pupil_list.visibility = View.VISIBLE
                adapter.setTotalPages(state.totalPages)
            }
            is Loading -> {
                loading_content.visibility = View.VISIBLE
            }
            is Error -> {
                loading_content.visibility = View.GONE
                // If DB is empty and there was an error while fetching the first page.
                pupilViewModel.retryGetPupilList()
            }
        }

    }
    private val dbPupilListObserver = Observer<List<Pupil>> { pupilList ->
        // update adapter.
        adapter.updateTeamList(pupilList)
    }

    private fun searchMorePupils(page: Int) {
        if (page < adapter.getTotalPages())
            pupilViewModel.getPupilList(page)
    }


}
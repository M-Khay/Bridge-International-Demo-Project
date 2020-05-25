package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.network.ApiResult
import com.bridge.androidtechnicaltest.network.Error
import com.bridge.androidtechnicaltest.network.Success
import com.yourself.searchyourcityweather.utils.NetworkConnectivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), PupilListFragment.ChangeFragmentListener {
    private val pupilViewModel by viewModel<PupilViewModel>()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            fm.beginTransaction()
                    .add(R.id.container, PupilListFragment())
                    .commit()
        }



        pupilViewModel.newPupilSyncState.observe(this, syncStateObeserver)
    }

    private val syncStateObeserver = Observer<ApiResult<Any>> { state ->
        when (state) {
            is Success -> {
                Toast.makeText(this, "All Data Synced Successfully", Toast.LENGTH_LONG).show()
            }
            is Error -> {
                Toast.makeText(this, "Please try again later", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun syncNewPupilData() {
        if (NetworkConnectivity.isNetworkConnected) {
            pupilViewModel.syncNewPupilList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_reset) {
            syncNewPupilData()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun changeFragment() {
        val addPupilFragment = AddPupilFragment()
        val fm = supportFragmentManager
        fm.beginTransaction()
                .replace(R.id.container, addPupilFragment)
                .addToBackStack(null)
                .commit()
    }
}
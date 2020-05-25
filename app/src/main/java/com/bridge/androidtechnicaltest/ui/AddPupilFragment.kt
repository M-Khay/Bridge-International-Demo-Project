package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.NewPupil
import kotlinx.android.synthetic.main.fragment_add_pupil.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddPupilFragment : Fragment() {

    private val pupilViewModel by viewModel<PupilViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pupil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        pupilViewModel.addNewPupilState.observe(this, addNewPupilObserver)

        save.setOnClickListener {
            val name = name.text.toString()
            val country = country.text.toString()

            if (validateData(name, country)) {
                val newPupil = NewPupil(1, name, country)
                pupilViewModel.addNewPupil(newPupil)
            } else {
                Toast.makeText(activity, "Please Correct Errors As Indicated.", Toast.LENGTH_LONG).show()
            }

        }
    }

    private val addNewPupilObserver = Observer<Boolean> { state ->

        if (state) {
            activity?.onBackPressed()
        } else {
            Toast.makeText(activity, "Oops!! Its not you its us, please try again", Toast.LENGTH_LONG).show()
        }
    }

    private fun validateData(name: String, country: String): Boolean {

        if (name.isEmpty()) {
            name_input_layout.error = resources.getString(R.string.pupil_detail_name_error)
            return false
        }
        if (country.isEmpty()) {
            country_input_layout.error = resources.getString(R.string.pupil_detail_country_error)
            return false
        }
        return true
    }
}

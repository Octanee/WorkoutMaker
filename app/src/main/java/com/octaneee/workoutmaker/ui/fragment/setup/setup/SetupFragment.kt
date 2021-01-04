package com.octaneee.workoutmaker.ui.fragment.setup.setup

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_setup.*

@AndroidEntryPoint
class SetupFragment : Fragment(R.layout.fragment_setup) {

    private val viewModel: SetupViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNameEditText()
        setupHeightEditText()
        setupSaveButton()
    }

    private fun setupHeightEditText() {
        setupFragmentHeightEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    viewModel.userHeight = s.toString().toInt()
                } else {
                    viewModel.userHeight = 0
                }
            }

        })
    }

    private fun setupNameEditText() {
        setupFragmentNameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.userName = s.toString()
            }

        })
    }

    private fun setupSaveButton() {
        setupFragmentSaveButton.setOnClickListener {
            if (checkUserName() && checkUserHeight()) {

                viewModel.saveUserData()
                findNavController().navigate(R.id.mainActivity)
            }
        }
    }

    private fun checkUserName(): Boolean {
        if (viewModel.userName.isNotEmpty()) {
            return true
        }
        Toast.makeText(context, "Enter a user name.", Toast.LENGTH_SHORT).show()
        return false
    }

    private fun checkUserHeight(): Boolean {
        if (viewModel.userHeight > 0) {
            return true
        }
        Toast.makeText(context, "Enter a height.", Toast.LENGTH_SHORT).show()
        return false
    }
}
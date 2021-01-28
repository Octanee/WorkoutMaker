package com.octaneee.workoutmaker.ui.setup.fragment.setup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
        setupFragmentHeightEditText.addTextChangedListener {
            if (it.toString().isNotEmpty()) {
                viewModel.userHeight = it.toString().toInt()
            } else {
                viewModel.userHeight = 0
            }
        }
    }

    private fun setupNameEditText() {
        setupFragmentNameEditText.addTextChangedListener {
            viewModel.userName = it.toString()
        }
    }

    private fun setupSaveButton() {
        setupFragmentSaveButton.setOnClickListener {
            if (checkUserName() && checkUserHeight()) {
                viewModel.saveUserData()

                findNavController().navigate(R.id.mainActivitySetup)
                requireActivity().finish()
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
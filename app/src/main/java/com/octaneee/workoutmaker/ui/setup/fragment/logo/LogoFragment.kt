package com.octaneee.workoutmaker.ui.setup.fragment.logo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoFragment : Fragment(R.layout.fragment_logo) {

    private val viewModel: LogoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.firstTime.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_logoFragment_to_setupFragment)
            else {
                findNavController().navigate(R.id.mainActivitySetup)
                requireActivity().finish()
            }
        }
    }
}
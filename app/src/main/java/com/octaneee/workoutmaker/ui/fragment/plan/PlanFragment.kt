package com.octaneee.workoutmaker.ui.fragment.plan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.fragment.plan.viewmodel.PlanFragmentViewModel

class PlanFragment : Fragment(R.layout.fragment_plan) {

    private val viewModel: PlanFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentMacrocycle()
    }
}
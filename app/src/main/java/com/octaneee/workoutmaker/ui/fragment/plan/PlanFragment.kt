package com.octaneee.workoutmaker.ui.fragment.plan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.activity.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.viewmodel.PlanFragmentViewModel

class PlanFragment : Fragment(R.layout.fragment_plan) {

    private val viewModel: PlanFragmentViewModel by viewModels()
    private val mainViewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val macrocycle = viewModel.getCurrentMacrocycle(mainViewModel.user)
    }
}
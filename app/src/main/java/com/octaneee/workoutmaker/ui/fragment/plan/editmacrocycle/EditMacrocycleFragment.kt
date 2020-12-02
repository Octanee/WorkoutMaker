package com.octaneee.workoutmaker.ui.fragment.plan.editmacrocycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.logic.utility.TAG

class EditMacrocycleFragment : Fragment() {

    private val args by navArgs<EditMacrocycleFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d(TAG, "onCreateView: ${args.macrocycleWithMesocycles}")

        return inflater.inflate(R.layout.fragment_edit_macrocycle, container, false)
    }
}
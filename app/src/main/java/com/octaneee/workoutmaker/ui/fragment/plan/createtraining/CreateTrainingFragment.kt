package com.octaneee.workoutmaker.ui.fragment.plan.createtraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.fragment_create_training.*

class CreateTrainingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCreateTrainingFragmentAddExerciseToTrainingFAB()
    }

    private fun setUpCreateTrainingFragmentAddExerciseToTrainingFAB() {
        createTrainingFragmentAddExerciseToTrainingFAB.setOnClickListener {
            findNavController().navigate(R.id.action_createTrainingFragment_to_addExerciseToTrainignFragment)
        }
    }
}
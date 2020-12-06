package com.octaneee.workoutmaker.ui.fragment.plan.addexercisetotraining

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.fragment_add_exercise_to_training.*

class AddExerciseToTrainingFragment : Fragment() {

    companion object {
        const val TAG = "AddExerciseToTraining"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_exercise_to_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        setUpCreateTrainingFragmentAddExerciseFAB()
    }

    private fun setUpCreateTrainingFragmentAddExerciseFAB() {
        Log.d(TAG, "setUpCreateTrainingFragmentAddExerciseFAB: ")
        createTrainingFragmentAddExerciseFAB.setOnClickListener {
            findNavController().navigate(R.id.action_addExerciseToTrainingFragment_to_exerciseListSortTypeFragment)
        }
    }
}
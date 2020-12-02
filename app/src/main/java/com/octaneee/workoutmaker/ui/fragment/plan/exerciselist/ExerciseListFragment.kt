package com.octaneee.workoutmaker.ui.fragment.plan.exerciselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.fragment_exercise_list.*

class ExerciseListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_exercise_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpCreateTrainingFragmentNewExerciseFAB()
    }

    private fun setUpCreateTrainingFragmentNewExerciseFAB() {
        createTrainingFragmentNewExerciseFAB.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_createExerciseFragment)
        }
    }
}
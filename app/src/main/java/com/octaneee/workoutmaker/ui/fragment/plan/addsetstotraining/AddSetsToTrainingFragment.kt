package com.octaneee.workoutmaker.ui.fragment.plan.addsetstotraining

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.fragment_add_sets_to_training.view.*

class AddSetsToTrainingFragment : Fragment() {

    companion object {
        const val TAG = "AddExerciseToTraining"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_sets_to_training, container, false)

        setUpFAB(view.addSetsToTrainingFragmentAddSetFAB)

        return view

    }

    private fun setUpFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {

        }
    }
}
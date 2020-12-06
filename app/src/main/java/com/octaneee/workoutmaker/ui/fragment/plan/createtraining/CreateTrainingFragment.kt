package com.octaneee.workoutmaker.ui.fragment.plan.createtraining

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.fragment.plan.createtraining.viewmodel.CreateTrainingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_training.view.*

class CreateTrainingFragment : Fragment() {

    private val args: CreateTrainingFragmentArgs by navArgs()
    private val viewModel: CreateTrainingFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_training, container, false)

        setUpFromArgs()
        setUpAddExerciseToTrainingFAB(view.createTrainingFragmentAddExerciseToTrainingFAB)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_training_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createTrainingFragmentMenuSave -> menuSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        if (validateDate()) {
            val list = viewModel.microcycleWithTrainings.trainings.toMutableList()
            list.add(viewModel.trainingWithString)
            viewModel.microcycleWithTrainings.trainings = list
            val action =
                CreateTrainingFragmentDirections.actionCreateTrainingFragmentToCreateMicrocycleFragment(
                    viewModel.macrocycleWithMesocycles,
                    viewModel.mesocycleAndMesocycleTypeWithMicrocycles,
                    viewModel.microcycleWithTrainings
                )
            findNavController().navigate(action)
        }
    }

    private fun validateDate(): Boolean {
        return checkName() && checkDayOfMicrocycle() && checkSets()
    }

    private fun checkSets(): Boolean {
        TODO("Not yet implemented")
    }

    private fun checkDayOfMicrocycle(): Boolean {
        TODO("Not yet implemented")
    }

    private fun checkName(): Boolean {
        TODO("Not yet implemented")
    }

    private fun setUpFromArgs() {
        TODO("Not yet implemented")
    }

    private fun setUpAddExerciseToTrainingFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_createTrainingFragment_to_addExerciseToTrainingFragment)
        }
    }
}
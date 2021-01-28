package com.octaneee.workoutmaker.ui.main.fragment.workout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.ExerciseWithSeriesAdapter
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_workout.*

@AndroidEntryPoint
class WorkoutFragment : Fragment(), ItemListener {

    private val viewModel: WorkoutViewModel by viewModels()
    private lateinit var adapter: ExerciseWithSeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStartButton()
        setupRecyclerView()
        viewModel.setTrainingWithExercises(1)

        viewModel.trainingWithExercises.observe(viewLifecycleOwner) {
            adapter.updateDataSet(it.exerciseWithSeries)
        }
    }

    private fun setupRecyclerView() {
        adapter = ExerciseWithSeriesAdapter(this)

        val recyclerView = workoutFragmentRecyclerView

        recyclerView.apply {
            adapter = this@WorkoutFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = 0,
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupStartButton() {
        workoutFragmentStartButton.setOnClickListener {
            viewModel.workoutManager.startWorkout(viewModel.trainingWithExercises.value!!)

            findNavController().navigate(R.id.currentWorkoutActivity)
            requireActivity().finish()
        }
    }

    override fun onItemClick(position: Int) {
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
    }

    override fun onItemSwipeRight(position: Int) {
    }
}
package com.octaneee.workoutmaker.ui.current_workout.fragment.exercise_list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Chronometer
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.WorkoutExerciseAdapter
import com.octaneee.workoutmaker.ui.current_workout.activity.CurrentWorkoutActivity
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_workout.*
import kotlinx.android.synthetic.main.fragment_current_workout_exercise_list.*

@AndroidEntryPoint
class CurrentWorkoutExerciseListFragment :
    Fragment(R.layout.fragment_current_workout_exercise_list),
    ItemListener {

    private val viewModel: CurrentWorkoutExerciseListViewModel by viewModels()
    private lateinit var chronometer: Chronometer
    private lateinit var adapter: WorkoutExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.workoutManager.training.observe(viewLifecycleOwner) {
            requireActivity().workoutToolbar.title = it.trainingName
        }

        viewModel.workoutManager.exercises.observe(viewLifecycleOwner) {
            adapter.updateDataSet(it)
        }

        setupChronometer()
    }

    private fun setupChronometer() {
        chronometer = workoutExerciseListFragmentChronometer
        viewModel.workoutManager.startTime.observe(viewLifecycleOwner) {
            chronometer.base = it
        }
        chronometer.start()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.current_workout_exercise_list_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.currentWorkoutExerciseListMenuStop -> stopWorkout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun stopWorkout() {
        stopWorkoutService()


        findNavController().navigate(R.id.mainActivityWorkout)
        requireActivity().finish()
    }

    private fun stopWorkoutService() {
        val currentWorkoutActivity = activity as CurrentWorkoutActivity
        currentWorkoutActivity.stopWorkoutService()
    }

    private fun setupRecyclerView() {
        adapter = WorkoutExerciseAdapter(this)

        val recyclerView = workoutExerciseListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@CurrentWorkoutExerciseListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = 0
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onItemClick(position: Int) {
        viewModel.workoutManager.setCurrentExercise(position)
        val action =
            CurrentWorkoutExerciseListFragmentDirections.actionWorkoutExerciseListFragmentToExerciseSeriesListFragment()
        findNavController().navigate(action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
    }

    override fun onItemSwipeRight(position: Int) {
    }
}
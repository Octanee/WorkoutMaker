package com.octaneee.workoutmaker.ui.current_workout.fragment.series_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.ExerciseSeriesAdapter
import com.octaneee.workoutmaker.model.entity.SeriesLog
import com.octaneee.workoutmaker.ui.bottom_sheet.series_log.SeriesLogBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.timer.TimerBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_workout.*
import kotlinx.android.synthetic.main.fragment_current_workout_exercise_series_list.*

@AndroidEntryPoint
class CurrentWorkoutExerciseSeriesListFragment :
    Fragment(R.layout.fragment_current_workout_exercise_series_list), ItemListener,
    SeriesLogBottomSheetFragment.SeriesLogBottomSheetFragmentListener,
    TimerBottomSheetFragment.TimerBottomSheetFragmentListener {

    private val viewModel: CurrentWorkoutExerciseSeriesListViewModel by viewModels()

    private lateinit var seriesLogBottomSheet: SeriesLogBottomSheetFragment
    private lateinit var timerBottomSheet: TimerBottomSheetFragment
    private lateinit var adapter: ExerciseSeriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupFab()

        viewModel.workoutManager.currentExercise!!.let {
            requireActivity().workoutToolbar.title = it.exercise.exerciseName
            adapter.updateDataSet(it.series)
        }

        setupTimerBottomSheet()
        setupSeriesLogBottomSheet()
    }

    private fun setupTimerBottomSheet() {
        timerBottomSheet = TimerBottomSheetFragment(this)
    }

    private fun setupSeriesLogBottomSheet() {
        seriesLogBottomSheet = SeriesLogBottomSheetFragment(this)
    }

    private fun setupRecyclerView() {
        adapter = ExerciseSeriesAdapter(this)

        val recyclerView = exerciseSeriesListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@CurrentWorkoutExerciseSeriesListFragment.adapter
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

    private fun setupFab() {
        exerciseSeriesListFragmentFAB.setOnClickListener {
            if (viewModel.workoutManager.currentSeries == null) {
                val action =
                    CurrentWorkoutExerciseSeriesListFragmentDirections.actionExerciseSeriesListFragmentToWorkoutExerciseListFragment()
                findNavController().navigate(action)
            } else {
                if (viewModel.restTime) {
                    showTimer()
                } else {
                    setupSeriesLog()
                    showSeriesLog()
                }
            }
        }
    }

    private fun showTimer() {
        timerBottomSheet.show(
            requireActivity().supportFragmentManager,
            "TimerBottomSheetFragment"
        )
    }

    private fun setupTimer() {
        timerBottomSheet.setup(viewModel.workoutManager.currentSeries!!.series.restTime ?: 1)
    }

    private fun setupSeriesLog() {
        seriesLogBottomSheet.setupSeries(viewModel.workoutManager.currentSeries!!.series)
    }

    private fun showSeriesLog() {
        seriesLogBottomSheet.show(
            requireActivity().supportFragmentManager,
            "SeriesLogBottomSheet"
        )
    }

    override fun onItemClick(position: Int) {
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
    }

    override fun onItemSwipeRight(position: Int) {
    }

    override fun onSeriesBottomSheetFragmentLogButtonClick(log: SeriesLog) {
        seriesLogBottomSheet.dismiss()

        viewModel.restTime = true
        setupTimer()

        viewModel.workoutManager.currentSeries!!.isCompleted = true
        viewModel.workoutManager.logs.add(log)

        showTimer()
    }

    override fun onTimerStop() {
        timerBottomSheet.dismiss()

        viewModel.restTime = false
        viewModel.workoutManager.nextSeries()
    }

}
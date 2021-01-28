package com.octaneee.workoutmaker.ui.bottom_sheet.series_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.SetListBottomSheetAdapter
import com.octaneee.workoutmaker.model.SimpleExercise
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.model.relations.ExerciseWithSeries
import com.octaneee.workoutmaker.other.extension.addItem
import com.octaneee.workoutmaker.other.extension.removeItemAt
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.bottom_sheet_set_list.*

@AndroidEntryPoint
class SeriesListBottomSheetFragment(private val listener: SetsBottomSheetFragmentListener) :
    BottomSheetDialogFragment(), SetListBottomSheetAdapter.SetListItemListener {

    private lateinit var dataSet: MutableList<Series>
    private val viewModel: SeriesListBottomSheetViewModel by viewModels()

    private lateinit var adapter: SetListBottomSheetAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_set_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupExerciseName()
        setupAddSetButton()
        setupRecyclerView()
        setupSaveButton()

        viewModel.dataSet.value = dataSet

        viewModel.dataSet.observe(viewLifecycleOwner) {
            setSetsNumber(it.size)
            adapter.updateDataSet(it)
        }
    }

    override fun onSetListItemMinimumChange(position: Int, value: Int?) {
        val item = viewModel.dataSet.value!![position]
        item.minimum = value
    }

    override fun onSetListItemMaximumChange(position: Int, value: Int?) {
        val item = viewModel.dataSet.value!![position]
        item.maximum = value
    }

    override fun onSetListItemRestTimeChange(position: Int, value: Int?) {
        val item = viewModel.dataSet.value!![position]
        item.restTime = value
    }

    override fun onItemClick(position: Int) {
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        viewModel.dataSet.removeItemAt(position)
    }

    override fun onItemSwipeRight(position: Int) {
    }

    fun setup(item: Exercise) {
        viewModel.exercise = SimpleExercise(item.exerciseId, item.exerciseName)
        dataSet = mutableListOf()
        dataSet.add(Series())
    }

    fun setup(item: ExerciseWithSeries) {
        viewModel.exercise = item.exercise
        dataSet = mutableListOf()
        dataSet.addAll(item.seriesList)
        viewModel.trainingExerciseCrossRefId =
            item.trainingExerciseCrossRef.trainingExerciseCrossRefId
    }

    private fun setupSaveButton() {
        bottomSheetSetListSaveButton.setOnClickListener {
            listener.onSetsBottomSheetFragmentSaveButtonClick(
                viewModel.dataSet.value ?: listOf(),
                viewModel.exercise.exerciseId,
                viewModel.trainingExerciseCrossRefId
            )
        }
    }

    private fun setupAddSetButton() {
        bottomSheetSetListAddSetButton.setOnClickListener {
            viewModel.dataSet.addItem(Series(trainingExerciseCrossRefIdFk = viewModel.trainingExerciseCrossRefId))
            adapter.notifyDataSetChanged()
        }
    }

    private fun setupExerciseName() {
        bottomSheetSetListExerciseNameTextView.text = viewModel.exercise.exerciseName
    }

    private fun setSetsNumber(number: Int) {
        bottomSheetSetListNumberOfSetsTextView.text = number.toString()
    }

    private fun setupRecyclerView() {
        adapter = SetListBottomSheetAdapter(this)
        val recyclerView = bottomSheetSetLisRecyclerView
        recyclerView.apply {
            adapter = this@SeriesListBottomSheetFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = ItemTouchHelper.LEFT
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    interface SetsBottomSheetFragmentListener {
        fun onSetsBottomSheetFragmentSaveButtonClick(
            dataSet: List<Series>,
            exerciseId: Long,
            trainingExerciseCrossRefId: Long?
        )
    }
}
package com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.adapter.ExerciseListSortTypeFragmentRecyclerViewAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.viewmodel.ExerciseListSortTypeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_exercise_list_sort_type.*


class ExerciseListSortTypeFragment : Fragment() {

    private val viewModel: ExerciseListSortTypeFragmentViewModel by viewModels()
    private lateinit var exerciseListSortTypeFragmentRecyclerViewAdapter: ExerciseListSortTypeFragmentRecyclerViewAdapter

    companion object {
        const val TAG = "ExerciseListSortType"
    }

    enum class SortType {
        Muscle,
        Equipment,
        ExerciseType
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d(TAG, "onCreate: ")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.fragment_exercise_list_sort_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")

        viewModel.equipmentListLiveData.observe(viewLifecycleOwner, {
            viewModel.equipmentList = it
            updateRecyclerView()
        })
        viewModel.muscleListLiveData.observe(viewLifecycleOwner, {
            viewModel.muscleList = it
            updateRecyclerView()
        })
        viewModel.exerciseTypeListLiveData.observe(viewLifecycleOwner, {
            viewModel.exerciseTypeList = it
            updateRecyclerView()
        })
        setUpExerciseListSortTypeFragmentRecyclerView()
    }

    private fun updateRecyclerView() {
        when (viewModel.sortType) {
            SortType.ExerciseType -> exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
                SortType.ExerciseType,
                viewModel.exerciseTypeList
            )
            SortType.Equipment -> exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
                SortType.Equipment,
                viewModel.equipmentList
            )
            SortType.Muscle -> exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
                SortType.Muscle,
                viewModel.muscleList
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercise_list_sort_type_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exerciseListSortTypeFragmentMenuMuscle -> setSortTypeMuscle()
            R.id.exerciseListSortTypeFragmentMenuEquipment -> setSortTypeEquipment()
            R.id.exerciseListSortTypeFragmentMenuExerciseType -> setSortTypeExerciseType()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSortTypeExerciseType() {
        exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
            SortType.ExerciseType,
            viewModel.exerciseTypeList
        )
        viewModel.sortType = SortType.ExerciseType
    }

    private fun setSortTypeEquipment() {
        exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
            SortType.Equipment,
            viewModel.equipmentList
        )
        viewModel.sortType = SortType.Equipment
    }

    private fun setSortTypeMuscle() {
        exerciseListSortTypeFragmentRecyclerViewAdapter.updateDataSet(
            SortType.Muscle,
            viewModel.muscleList
        )
        viewModel.sortType = SortType.Muscle
    }

    private fun setUpExerciseListSortTypeFragmentRecyclerView() {
        Log.d(TAG, "setUpExerciseListSortTypeFragmentRecyclerView: ")
        val recyclerView = exerciseListSortTypeFragmentRecyclerView

        val list = when (viewModel.sortType) {
            SortType.Equipment -> viewModel.equipmentList
            SortType.ExerciseType -> viewModel.exerciseTypeList
            SortType.Muscle -> viewModel.muscleList
        }

        exerciseListSortTypeFragmentRecyclerViewAdapter =
            ExerciseListSortTypeFragmentRecyclerViewAdapter(list, viewModel)

        with(recyclerView) {
            adapter = exerciseListSortTypeFragmentRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}
package com.octaneee.workoutmaker.ui.main.fragment.plan.exercise_list

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.ExerciseListAdapter
import com.octaneee.workoutmaker.model.entity.*
import com.octaneee.workoutmaker.other.ExerciseOrderType
import com.octaneee.workoutmaker.ui.bottom_sheet.exercise.ExerciseBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.exercise_filter.ExerciseFilterBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.series_list.SeriesListBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_exercise_list.*

@AndroidEntryPoint
class ExerciseListFragment : Fragment(R.layout.fragment_exercise_list), ItemListener,
    ExerciseFilterBottomSheetFragment.ExerciseFilterBottomSheetFragmentListener,
    SeriesListBottomSheetFragment.SetsBottomSheetFragmentListener,
    ExerciseBottomSheetFragment.ExerciseBottomSheetFragmentListener {

    private val viewModel: ExerciseListViewModel by viewModels()
    private val args: ExerciseListFragmentArgs by navArgs()
    private lateinit var adapter: ExerciseListAdapter
    private lateinit var exerciseFilterBottomSheetFragment: ExerciseFilterBottomSheetFragment
    private lateinit var seriesListBottomSheetFragment: SeriesListBottomSheetFragment
    private lateinit var exerciseBottomSheetFragment: ExerciseBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupExerciseFilterDialogFragment()
        setupExerciseDialogFragment()
        setupSetListDialogFragment()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercise_list_fragment_menu, menu)
        setupSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exerciseListFragmentMenuFilter -> menuShowFilter()
            R.id.exerciseListFragmentMenuAdd -> menuAdd()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        seriesListBottomSheetFragment.setup(item)
        seriesListBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "SeriesListBottomSheetFragment"
        )
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.exerciseName}\"?")
            setCancelable(false)
            setNegativeButtonIcon(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_close,
                    null
                )
            )
            setPositiveButtonIcon(
                ResourcesCompat.getDrawable(
                    context.resources,
                    R.drawable.ic_delete,
                    null
                )
            )
            setNegativeButton("Cancel") { _, _ ->
                adapter.notifyItemChanged(position)
            }
            setPositiveButton("Delete") { _, _ ->
                viewModel.deleteExercise(item)
                adapter.notifyItemRemoved(position)
            }
        }

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).apply {
            setBackgroundColor(ResourcesCompat.getColor(resources, R.color.paradise_pink, null))
            setTextColor(ResourcesCompat.getColor(resources, R.color.cultured, null))
        }
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).apply {
            setTextColor(ResourcesCompat.getColor(resources, R.color.cultured, null))
        }
    }

    override fun onItemSwipeRight(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        val action =
            ExerciseListFragmentDirections.actionExerciseListFragmentToExerciseFragment(item.exerciseId)
        findNavController().navigate(action)
    }

    override fun onExerciseFilterBottomSheetFragmentApplyButtonClick() {
        exerciseFilterBottomSheetFragment.dismiss()
        viewModel.getFilteredExerciseList().observe(viewLifecycleOwner) {
            adapter.updateDataSet(it)
        }
    }

    override fun onExerciseFilterBottomSheetFragmentCheckedChange(orderType: ExerciseOrderType) {
        viewModel.orderType = orderType
    }

    override fun onExerciseFilterBottomSheetFragmentMuscleSpinnerItemClick(muscle: Muscle?) {
        viewModel.sortMuscle = muscle
    }

    override fun onExerciseFilterBottomSheetFragmentExerciseTypeSpinnerItemClick(exerciseType: ExerciseType?) {
        viewModel.sortExerciseType = exerciseType
    }

    override fun onExerciseFilterBottomSheetFragmentEquipmentSpinnerItemClick(equipment: Equipment?) {
        viewModel.sortEquipment = equipment
    }

    override fun onSetsBottomSheetFragmentSaveButtonClick(
        dataSet: List<Series>,
        exerciseId: Long,
        trainingExerciseCrossRefId: Long?
    ) {
        viewModel.saveSets(dataSet, args.trainingId, exerciseId)
        seriesListBottomSheetFragment.dismiss()
        val action =
            ExerciseListFragmentDirections.actionExerciseListFragmentToSetListFragment(args.trainingId)
        findNavController().navigate(action)
    }

    override fun onExerciseBottomSheetFragmentSaveButtonClick(exerciseName: String) {
        exerciseBottomSheetFragment.dismiss()
        val exerciseId = viewModel.insertExercise(Exercise(exerciseName))
        val action =
            ExerciseListFragmentDirections.actionExerciseListFragmentToExerciseFragment(exerciseId)
        findNavController().navigate(action)
    }

    private fun menuAdd() {
        exerciseBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "ExerciseBottomSheetFragment"
        )
    }

    private fun menuShowFilter() {
        exerciseFilterBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "ExerciseFilterDialogFragment"
        )
    }

    private fun setupSearchView(menu: Menu) {
        val item = menu.findItem(R.id.exerciseListFragmentMenuSearch)

        val view = item?.actionView as SearchView
        view.queryHint = "Search exercise"
        view.imeOptions = EditorInfo.IME_ACTION_DONE

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = ExerciseListAdapter(this)
        val recyclerView = exerciseListFragmentRecyclerView
        recyclerView.apply {
            adapter = this@ExerciseListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupExerciseFilterDialogFragment() {
        exerciseFilterBottomSheetFragment = ExerciseFilterBottomSheetFragment(this)

        exerciseFilterBottomSheetFragment.setup(
            viewModel.orderType,
            viewModel.sortMuscle,
            viewModel.sortEquipment,
            viewModel.sortExerciseType
        )
    }

    private fun setupSetListDialogFragment() {
        seriesListBottomSheetFragment = SeriesListBottomSheetFragment(this)
    }

    private fun setupExerciseDialogFragment() {
        exerciseBottomSheetFragment = ExerciseBottomSheetFragment(this)
    }

    private fun setupObservers() {
        viewModel.exerciseList.observe(viewLifecycleOwner) {
            adapter.updateDataSet(it)
        }

        viewModel.muscleList.observe(viewLifecycleOwner, {
            exerciseFilterBottomSheetFragment.updateMuscleDataSet(it)
        })

        viewModel.equipmentList.observe(viewLifecycleOwner, {
            exerciseFilterBottomSheetFragment.updateEquipmentDataSet(it)
        })

        viewModel.exerciseTypeList.observe(viewLifecycleOwner, {
            exerciseFilterBottomSheetFragment.updateExerciseTypeDataSet(it)
        })
    }

}
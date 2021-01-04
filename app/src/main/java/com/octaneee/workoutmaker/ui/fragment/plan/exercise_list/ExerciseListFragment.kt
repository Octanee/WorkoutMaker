package com.octaneee.workoutmaker.ui.fragment.plan.exercise_list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.ExerciseListAdapter
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.model.entity.ExerciseType
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.model.entity.Set
import com.octaneee.workoutmaker.other.ExerciseOrderType
import com.octaneee.workoutmaker.ui.bottom_sheet.ExerciseFilterBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.set_list.SetListBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_exercise_list.*
import timber.log.Timber

@AndroidEntryPoint
class ExerciseListFragment : Fragment(R.layout.fragment_exercise_list), ItemListener,
    ExerciseFilterBottomSheetFragment.ExerciseFilterBottomSheetFragmentListener,
    SetListBottomSheetFragment.SetsBottomSheetFragmentListener {

    private val viewModel: ExerciseListViewModel by viewModels()
    private val args: ExerciseListFragmentArgs by navArgs()
    private lateinit var adapter: ExerciseListAdapter
    private lateinit var exerciseFilterBottomSheetFragment: ExerciseFilterBottomSheetFragment
    private lateinit var setListBottomSheetFragment: SetListBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupExerciseFilterDialogFragment()
        setupSetListDialogFragment()

        viewModel.exerciseList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.updateDataSet(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercise_list_fragment_menu, menu)
        setupSearchView(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exerciseListFragmentMenuFilter -> showFilter()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showFilter() {
        exerciseFilterBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "ExerciseFilterDialogFragment"
        )
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        setListBottomSheetFragment.setup(item)
        setListBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "SetListBottomSheetFragment"
        )
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        Toast.makeText(context, "$item - Left", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSwipeRight(position: Int) {
        val item = viewModel.exerciseList.value!![position]
        Toast.makeText(context, "$item - Right", Toast.LENGTH_SHORT).show()
    }

    override fun onExerciseFilterBottomSheetFragmentApplyButtonClick() {
        exerciseFilterBottomSheetFragment.dismiss()
        viewModel.getFilteredExerciseList().observe(viewLifecycleOwner) {
            Timber.d("GetFilteredExerciseList Observe: $it")
            adapter.updateDataSet(it)
        }
    }

    override fun onExerciseFilterBottomSheetFragmentCheckedChange(orderType: ExerciseOrderType) {
        Toast.makeText(context, orderType.toString(), Toast.LENGTH_SHORT).show()
        viewModel.orderType = orderType
    }

    override fun onExerciseFilterBottomSheetFragmentMuscleSpinnerItemClick(muscle: Muscle?) {
        Toast.makeText(context, muscle.toString(), Toast.LENGTH_SHORT).show()
        viewModel.sortMuscle = muscle
    }

    override fun onExerciseFilterBottomSheetFragmentExerciseTypeSpinnerItemClick(exerciseType: ExerciseType?) {
        Toast.makeText(context, exerciseType.toString(), Toast.LENGTH_SHORT).show()
        viewModel.sortExerciseType = exerciseType
    }

    override fun onExerciseFilterBottomSheetFragmentEquipmentSpinnerItemClick(equipment: Equipment?) {
        Toast.makeText(context, equipment.toString(), Toast.LENGTH_SHORT).show()
        viewModel.sortEquipment = equipment
    }

    override fun onSetsBottomSheetFragmentSaveButtonClick(
        dataSet: List<Set>,
        exerciseId: Long,
        trainingExerciseCrossRefId: Long?
    ) {
        viewModel.saveSets(dataSet, args.trainingId, exerciseId)
        setListBottomSheetFragment.dismiss()
        val action =
            ExerciseListFragmentDirections.actionExerciseListFragmentToSetListFragment(args.trainingId)
        findNavController().navigate(action)
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

    private fun setupSetListDialogFragment() {
        setListBottomSheetFragment = SetListBottomSheetFragment(this)
    }
}
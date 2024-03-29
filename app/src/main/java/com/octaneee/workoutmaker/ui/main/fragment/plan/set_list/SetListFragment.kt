package com.octaneee.workoutmaker.ui.main.fragment.plan.set_list

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.SetListAdapter
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.ui.bottom_sheet.series_list.SeriesListBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.training.TrainingBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_set_list.*


@AndroidEntryPoint
class SetListFragment : Fragment(R.layout.fragment_set_list), ItemListener,
    TrainingBottomSheetFragment.TrainingBottomSheetFragmentListener,
    SeriesListBottomSheetFragment.SetsBottomSheetFragmentListener {

    private val viewModel: SetListViewModel by viewModels()
    private val args: SetListFragmentArgs by navArgs()
    private lateinit var adapter: SetListAdapter
    private lateinit var trainingBottomSheetFragment: TrainingBottomSheetFragment
    private lateinit var seriesListBottomSheetFragment: SeriesListBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFromArgs()
        setupRecyclerView()
        setupTrainingBottomSheetFragment()
        setupSetListBottomSheetFragment()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.set_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.setListFragmentMenuAdd -> menuAdd()
            R.id.setListFragmentMenuEdit -> menuEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.dataSet.value!![position]
        seriesListBottomSheetFragment.setup(item)
        seriesListBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "SeriesListBottomSheetFragment"
        )
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.dataSet.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.exercise.exerciseName}\" from ${viewModel.training.value!!.trainingName}?")
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
                viewModel.deleteExercise(item.trainingExerciseCrossRef)
                adapter.notifyItemRemoved(position)
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
    }

    override fun onItemSwipeRight(position: Int) {
    }

    override fun onTrainingBottomSheetFragmentSaveButtonClick(
        trainingName: String,
        trainingNumberOfDay: Int
    ) {
        trainingBottomSheetFragment.dismiss()
        val item = viewModel.training.value!!
        if (!TextUtils.isEmpty(trainingName)) {
            item.trainingName = trainingName
        }
        item.dayOfMicrocycle = trainingNumberOfDay

        viewModel.updateTraining(item)
    }

    override fun onSetsBottomSheetFragmentSaveButtonClick(
        dataSet: List<Series>,
        exerciseId: Long,
        trainingExerciseCrossRefId: Long?
    ) {
        seriesListBottomSheetFragment.dismiss()
        val list = viewModel.dataSet.value!!
        val item = list.find {
            it.trainingExerciseCrossRef.trainingExerciseCrossRefId == trainingExerciseCrossRefId
        }!!

        val removeList = mutableListOf<Series>()
        if (dataSet.size < item.seriesList.size) {
            removeList.addAll(item.seriesList.takeLast(item.seriesList.size - dataSet.size))
        }

        for (i in dataSet.indices) {
            dataSet[i].seriesId = item.seriesList.getOrNull(i)?.seriesId ?: 0
        }

        viewModel.updateSetList(dataSet, removeList)
    }

    private fun menuAdd() {
        val action =
            SetListFragmentDirections.actionSetListFragmentToExerciseListFragment(viewModel.training.value!!.trainingId)
        findNavController().navigate(action)
    }

    private fun menuEdit() {
        trainingBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "TrainingBottomSheetFragment"
        )
    }

    private fun setupTrainingBottomSheetFragment() {
        trainingBottomSheetFragment = TrainingBottomSheetFragment(this)
    }

    private fun setupSetListBottomSheetFragment() {
        seriesListBottomSheetFragment = SeriesListBottomSheetFragment(this)
    }

    private fun setupRecyclerView() {
        adapter = SetListAdapter(this)
        val recyclerView = setListFragmentRecyclerView
        recyclerView.apply {
            adapter = this@SetListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupFromArgs() {
        viewModel.setTraining(args.trainingId)
        viewModel.setDataSet(args.trainingId)
    }

    private fun setupObservers() {
        viewModel.training.observe(viewLifecycleOwner) {

            requireActivity().mainToolbar.title = it.trainingName
            viewModel.setNumberOfDays(it.microcycleIdFk)
            trainingBottomSheetFragment.setup(it.trainingName, it.dayOfMicrocycle)

            viewModel.numberOfDays.observe(viewLifecycleOwner) { number ->
                trainingBottomSheetFragment.setNumberOfDays(number ?: 1)
            }
        }

        viewModel.dataSet.observe(viewLifecycleOwner) {
            adapter.updateDataSet(it)
        }
    }
}
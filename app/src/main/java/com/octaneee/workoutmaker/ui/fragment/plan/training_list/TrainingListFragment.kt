package com.octaneee.workoutmaker.ui.fragment.plan.training_list

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
import com.octaneee.workoutmaker.adapter.TrainingListAdapter
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.ui.bottom_sheet.MicrocycleBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.TrainingBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_training_list.*

@AndroidEntryPoint
class TrainingListFragment : Fragment(R.layout.fragment_training_list), ItemListener,
    MicrocycleBottomSheetFragment.MicrocycleBottomSheetFragmentListener,
    TrainingBottomSheetFragment.TrainingBottomSheetFragmentListener {

    private val viewModel: TrainingListViewModel by viewModels()
    private val args: TrainingListFragmentArgs by navArgs()
    private lateinit var adapter: TrainingListAdapter
    private lateinit var microcycleBottomSheetFragment: MicrocycleBottomSheetFragment
    private lateinit var trainingBottomSheetFragment: TrainingBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFromArgs()
        setupRecyclerView()
        setupMicrocycleBottomSheetFragment()
        setupTrainingBottomSheetFragment()

        viewModel.microcycle.observe(viewLifecycleOwner, {
            requireActivity().mainToolbar.title = it.microcycleName
            microcycleBottomSheetFragment.setup(it.microcycleName, it.numberOfDays.toString())
            trainingBottomSheetFragment.setNumberOfDays(it.numberOfDays)
        })

        viewModel.trainingList.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.training_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.trainingListFragmentMenuAdd -> menuAdd()
            R.id.trainingListFragmentMenuEdit -> menuEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.trainingList.value!![position]
        val action =
            TrainingListFragmentDirections.actionTrainingListFragmentToSetListFragment(item.trainingId)
        findNavController().navigate(action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.trainingList.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.trainingName}\"")
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
                viewModel.deleteTraining(item)
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

    override fun onMicrocycleBottomSheetFragmentSaveButtonClick() {
        microcycleBottomSheetFragment.dismiss()
        val item = viewModel.microcycle.value!!
        if (!TextUtils.isEmpty(viewModel.newMicrocycleName)) {
            item.microcycleName = viewModel.newMicrocycleName!!
        }
        if (!TextUtils.isEmpty(viewModel.newMicrocycleNumberOfDays)) {
            item.numberOfDays = viewModel.newMicrocycleNumberOfDays!!.toInt()
        }
        viewModel.updateMicrocycle(item)
    }

    override fun onMicrocycleBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newMicrocycleName = sequence
    }

    override fun onMicrocycleBottomSheetFragmentNumberOfDaysEditTextChange(sequence: String?) {
        viewModel.newMicrocycleNumberOfDays = sequence
    }

    override fun onTrainingBottomSheetFragmentSaveButtonClick() {
        if (!TextUtils.isEmpty(viewModel.newTrainingName) && viewModel.newTrainingNumberOfDay != null) {
            trainingBottomSheetFragment.dismiss()
            val item = Training(
                viewModel.newTrainingName!!,
                viewModel.microcycle.value!!.microcycleId,
                viewModel.newTrainingNumberOfDay!!,
            )
            val trainingId = viewModel.saveNewTraining(item)
            val action =
                TrainingListFragmentDirections.actionTrainingListFragmentToSetListFragment(
                    trainingId
                )
            findNavController().navigate(action)
        }
    }

    override fun onTrainingBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newTrainingName = sequence
    }

    override fun onTrainingBottomSheetFragmentNumberOfDaySpinnerItemSelected(item: Int) {
        viewModel.newTrainingNumberOfDay = item
    }

    private fun menuEdit() {
        microcycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MicrocycleBottomSheetFragment"
        )
    }

    private fun menuAdd() {
        trainingBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "TrainingBottomSheetFragment"
        )
    }

    private fun setupTrainingBottomSheetFragment() {
        trainingBottomSheetFragment =
            TrainingBottomSheetFragment(this)
    }

    private fun setupMicrocycleBottomSheetFragment() {
        microcycleBottomSheetFragment = MicrocycleBottomSheetFragment(this)
    }

    private fun setupRecyclerView() {
        adapter = TrainingListAdapter(this)

        val recyclerView = trainingListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@TrainingListFragment.adapter
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

    private fun setupFromArgs() {
        viewModel.setMicrocycleAndTrainingList(args.microcycleId)
    }
}
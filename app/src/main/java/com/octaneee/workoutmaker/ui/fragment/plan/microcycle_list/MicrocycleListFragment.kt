package com.octaneee.workoutmaker.ui.fragment.plan.microcycle_list

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
import com.octaneee.workoutmaker.adapter.MicrocycleListAdapter
import com.octaneee.workoutmaker.model.entity.MesocycleType
import com.octaneee.workoutmaker.model.entity.Microcycle
import com.octaneee.workoutmaker.ui.bottom_sheet.MesocycleBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.MicrocycleBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_microcycle_list.*


@AndroidEntryPoint
class MicrocycleListFragment : Fragment(R.layout.fragment_microcycle_list), ItemListener,
    MesocycleBottomSheetFragment.MesocycleBottomSheetFragmentListener,
    MicrocycleBottomSheetFragment.MicrocycleBottomSheetFragmentListener {

    private val viewModel: MicrocycleListViewModel by viewModels()
    private val args: MicrocycleListFragmentArgs by navArgs()
    private lateinit var adapter: MicrocycleListAdapter
    private lateinit var mesocycleBottomSheetFragment: MesocycleBottomSheetFragment
    private lateinit var microcycleBottomSheetFragment: MicrocycleBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFromArgs()
        setupRecyclerView()
        setupMesocycleBottomSheetFragment()
        setupMicrocycleBottomSheetFragment()

        viewModel.mesocycle.observe(viewLifecycleOwner, {
            requireActivity().mainToolbar.title = it.mesocycleName
            mesocycleBottomSheetFragment.setup(it.mesocycleName, it.mesocycleTypeIdFk.toInt() - 1)
        })

        viewModel.microcycleList.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.microcycle_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.microcycleListFragmentMenuAdd -> menuAdd()
            R.id.microcycleListFragmentMenuEdit -> menuEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.microcycleList.value!![position]
        val action =
            MicrocycleListFragmentDirections.actionMicrocycleListFragmentToTrainingListFragment(item.microcycleId)
        findNavController().navigate(action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.microcycleList.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.microcycleName}\"")
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
                viewModel.deleteMicrocycle(item)
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

    override fun onMesocycleBottomSheetFragmentSaveButtonClick() {
        mesocycleBottomSheetFragment.dismiss()
        val item = viewModel.mesocycle.value!!
        if (!TextUtils.isEmpty(viewModel.newMesocycleName)) {
            item.mesocycleName = viewModel.newMesocycleName!!
        }
        if (viewModel.newMesocycleType != null) {
            item.mesocycleTypeIdFk = viewModel.newMesocycleType!!.mesocycleTypeId
        }
        viewModel.updateMesocycle(item)
    }

    override fun onMesocycleBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newMesocycleName = sequence
    }

    override fun onMesocycleBottomSheetFragmentSpinnerItemSelected(item: MesocycleType) {
        viewModel.newMesocycleType = item
    }

    override fun onMicrocycleBottomSheetFragmentSaveButtonClick() {
        if (!TextUtils.isEmpty(viewModel.newMicrocycleName) && !TextUtils.isEmpty(viewModel.newMicrocycleNumberOfDays)) {
            microcycleBottomSheetFragment.dismiss()
            val item = Microcycle(
                viewModel.newMicrocycleName!!,
                viewModel.mesocycle.value!!.mesocycleId,
                viewModel.newMicrocycleNumberOfDays!!.toInt(),
            )
            val microcycleId = viewModel.saveNewMicrocycle(item)
            val action =
                MicrocycleListFragmentDirections.actionMicrocycleListFragmentToTrainingListFragment(
                    microcycleId
                )
            findNavController().navigate(action)
        }
    }

    override fun onMicrocycleBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newMicrocycleName = sequence
    }

    override fun onMicrocycleBottomSheetFragmentNumberOfDaysEditTextChange(sequence: String?) {
        viewModel.newMicrocycleNumberOfDays = sequence
    }

    private fun menuEdit() {
        mesocycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MesocycleBottomSheetFragment"
        )
    }

    private fun menuAdd() {
        microcycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MicrocycleBottomSheetFragment"
        )
    }

    private fun setupRecyclerView() {
        adapter = MicrocycleListAdapter(this)

        val recyclerView = microcycleListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@MicrocycleListFragment.adapter
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
        viewModel.setMesocycleAndMicrocycleList(args.mesocycleId)
    }

    private fun setupMicrocycleBottomSheetFragment() {
        microcycleBottomSheetFragment = MicrocycleBottomSheetFragment(this)
    }

    private fun setupMesocycleBottomSheetFragment() {
        mesocycleBottomSheetFragment =
            MesocycleBottomSheetFragment(this)
        viewModel.mesocycleTypes.observe(viewLifecycleOwner, {
            mesocycleBottomSheetFragment.updateMesocycleTypes(it)
        })
    }
}
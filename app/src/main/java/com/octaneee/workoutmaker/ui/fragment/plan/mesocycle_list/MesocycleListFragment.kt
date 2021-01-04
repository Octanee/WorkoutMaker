package com.octaneee.workoutmaker.ui.fragment.plan.mesocycle_list

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
import com.octaneee.workoutmaker.adapter.MesocycleListAdapter
import com.octaneee.workoutmaker.model.entity.Mesocycle
import com.octaneee.workoutmaker.model.entity.MesocycleType
import com.octaneee.workoutmaker.ui.bottom_sheet.MacrocycleBottomSheetFragment
import com.octaneee.workoutmaker.ui.bottom_sheet.MesocycleBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_macrocycle.*
import kotlinx.android.synthetic.main.bottom_sheet_mesocycle.*
import kotlinx.android.synthetic.main.fragment_mesocycle_list.*
import timber.log.Timber

@AndroidEntryPoint
class MesocycleListFragment : Fragment(R.layout.fragment_mesocycle_list), ItemListener,
    MesocycleBottomSheetFragment.MesocycleBottomSheetFragmentListener,
    MacrocycleBottomSheetFragment.MacrocycleBottomSheetFragmentListener {

    private val viewModel: MesocycleListViewModel by viewModels()
    private val args: MesocycleListFragmentArgs by navArgs()
    private lateinit var adapter: MesocycleListAdapter
    private lateinit var macrocycleBottomSheetFragment: MacrocycleBottomSheetFragment
    private lateinit var mesocycleBottomSheetFragment: MesocycleBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFromArgs()
        setupRecyclerView()
        setupMacrocycleBottomSheetFragment()
        setupMesocycleBottomSheetFragment()

        viewModel.macrocycle.observe(viewLifecycleOwner, {
            requireActivity().mainToolbar.title = it.macrocycleName
            macrocycleBottomSheetFragment.setup(it.macrocycleName)
        })

        viewModel.mesocycleAndMesocycleTypeList.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mesocycle_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mesocycleListFragmentMenuAdd -> menuAdd()
            R.id.mesocycleListFragmentMenuEdit -> menuEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.mesocycleAndMesocycleTypeList.value!![position]
        val action =
            MesocycleListFragmentDirections.actionMesocycleListFragmentToMicrocycleListFragment(item.mesocycle.mesocycleId)
        findNavController().navigate(action)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {

    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.mesocycleAndMesocycleTypeList.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.mesocycle.mesocycleName}\"?")
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
                viewModel.deleteMesocycle(item.mesocycle)
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
    }

    override fun onMesocycleBottomSheetFragmentSaveButtonClick() {
        if (!TextUtils.isEmpty(viewModel.newMesocycleName) && viewModel.newMesocycleType != null) {
            mesocycleBottomSheetFragment.dismiss()
            val item = Mesocycle(
                viewModel.newMesocycleName!!,
                viewModel.newMesocycleType!!.mesocycleTypeId,
                viewModel.macrocycle.value!!.macrocycleId,
            )
            val mesocycleId = viewModel.saveNewMesocycle(item)
            val action =
                MesocycleListFragmentDirections.actionMesocycleListFragmentToMicrocycleListFragment(
                    mesocycleId
                )
            findNavController().navigate(action)
        }
    }

    override fun onMesocycleBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newMesocycleName = sequence
    }

    override fun onMesocycleBottomSheetFragmentSpinnerItemSelected(item: MesocycleType) {
        viewModel.newMesocycleType = item
    }

    override fun onMacrocycleBottomSheetFragmentSaveButtonClick() {
        macrocycleBottomSheetFragment.dismiss()
        val item = viewModel.macrocycle.value!!
        if (!TextUtils.isEmpty(viewModel.newMacrocycleName)) {
            item.macrocycleName = viewModel.newMacrocycleName!!
        }
        viewModel.updateMacrocycle(item)
    }

    override fun onMacrocycleBottomSheetFragmentNameEditTextChange(sequence: String?) {
        viewModel.newMacrocycleName = sequence
    }

    private fun setupRecyclerView() {
        adapter = MesocycleListAdapter(this)

        val recyclerView = mesocycleListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@MesocycleListFragment.adapter
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
        Timber.d("From Args: ${args.macrocycleId}")
        viewModel.setMacrocycleAndMesocycleList(args.macrocycleId)
    }

    private fun menuAdd() {
        mesocycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MesocycleBottomSheetFragment"
        )
    }

    private fun menuEdit() {
        macrocycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MacrocycleBottomSheetFragment"
        )
    }

    private fun setupMesocycleBottomSheetFragment() {
        mesocycleBottomSheetFragment =
            MesocycleBottomSheetFragment(this)
        viewModel.mesocycleTypes.observe(viewLifecycleOwner, {
            mesocycleBottomSheetFragment.updateMesocycleTypes(it)
        })
    }

    private fun setupMacrocycleBottomSheetFragment() {
        macrocycleBottomSheetFragment = MacrocycleBottomSheetFragment(this)
    }
}
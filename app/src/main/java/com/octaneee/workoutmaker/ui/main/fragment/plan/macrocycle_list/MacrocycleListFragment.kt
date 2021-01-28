package com.octaneee.workoutmaker.ui.main.fragment.plan.macrocycle_list

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.MacrocycleListAdapter
import com.octaneee.workoutmaker.model.entity.Macrocycle
import com.octaneee.workoutmaker.ui.bottom_sheet.macrocycle.MacrocycleBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_macrocycle_list.*
import kotlinx.android.synthetic.main.fragment_plan.*
import java.util.*

@AndroidEntryPoint
class MacrocycleListFragment : Fragment(R.layout.fragment_macrocycle_list), ItemListener,
    MacrocycleBottomSheetFragment.MacrocycleBottomSheetFragmentListener {

    private val viewModel: MacrocycleListViewModel by viewModels()
    private lateinit var adapter: MacrocycleListAdapter
    private lateinit var macrocycleBottomSheetFragment: MacrocycleBottomSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupBottomSheetFragment()

        viewModel.macrocycles.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.macrocycle_list_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.macrocycleListFragmentMenuAdd -> menuAdd()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.macrocycles.value!![position]
        viewModel.setCurrentMacrocycle(item.macrocycleId)
        findNavController().navigate(R.id.action_macrocycleListFragment_to_planFragment)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.macrocycles.value!![position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete \"${item.macrocycleName}\"?")
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
                viewModel.deleteMacrocycle(item)
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
        val item = viewModel.macrocycles.value!![position]
        val action =
            MacrocycleListFragmentDirections.actionMacrocycleListFragmentToMesocycleListFragment(
                item.macrocycleId
            )
        findNavController().navigate(action)
    }

    override fun onMacrocycleBottomSheetFragmentSaveButtonClick(
        macrocycleName: String,
        macrocycleStartDate: Date,
        macrocycleEndDate: Date?
    ) {
        if (!TextUtils.isEmpty(macrocycleName)) {
            macrocycleBottomSheetFragment.dismiss()
            val item = Macrocycle(macrocycleName, macrocycleStartDate, macrocycleEndDate)
            val macrocycleId = viewModel.addMacrocycle(item)
            val action =
                MacrocycleListFragmentDirections.actionMacrocycleListFragmentToMesocycleListFragment(
                    macrocycleId
                )
            findNavController().navigate(action)
        }
    }

    private fun menuAdd() {
        macrocycleBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "MacrocycleBottomSheetFragment"
        )
    }

    private fun setupRecyclerView() {
        adapter = MacrocycleListAdapter(this)

        val recyclerView = macrocycleListFragmentRecyclerView

        recyclerView.apply {
            adapter = this@MacrocycleListFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            )
        )

        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupBottomSheetFragment() {
        macrocycleBottomSheetFragment = MacrocycleBottomSheetFragment(this)
    }
}
package com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.other.disableDragDirection
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist.adapter.MacrocycleListFragmentAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.macrocyclelist.viewmodel.MacrocycleListFragmentViewModel


class MacrocycleListFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: MacrocycleListFragmentViewModel by viewModels()
    private lateinit var adapter: MacrocycleListFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_macrocycle_list, container, false)

        setUpRecyclerView(view.findViewById(R.id.macrocycleListFragmentRecyclerView))

        return view
    }

    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter = MacrocycleListFragmentAdapter(listOf(), mainActivityViewModel)

//        viewModel.macrocycles.observe(viewLifecycleOwner, {
//            updateAdapterDataSet(it)
//        })

        with(recyclerView) {
            adapter = this@MacrocycleListFragment.adapter
            layoutManager = LinearLayoutManager(context)
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            disableDragDirection()
        }
    }

    private fun onItemSwipeListener() = object : OnItemSwipeListener<Macrocycle> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: Macrocycle
        ): Boolean {
            return when (direction) {
                OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                    swipeRightToLeft(item)
                }
                OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                    swipeLeftToRight(item)
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun swipeLeftToRight(item: Macrocycle): Boolean {

//        viewModel.getMacrocycleWithMesocyclesById(item.macrocycleId).observe(viewLifecycleOwner, {
//            mainActivityViewModel.macrocycleWithMesocycles = it
//            val action =
//                MacrocycleListFragmentDirections.actionMacrocycleListFragmentToCreateMacrocycleFragment()
//            findNavController().navigate(action)
//        })

        return true
    }

    private fun swipeRightToLeft(item: Macrocycle): Boolean {
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle("Delete ${item.macrocycleName}?")
            setPositiveButton("Delete") { _, _ ->
                // viewModel.deleteMacrocycle(item)
            }
            setNegativeButton("Cancel", null)
            setCancelable(false)
        }
        val dialog = builder.create()
        dialog.show()
        return true
    }


    private fun updateAdapterDataSet(newDataSet: List<Macrocycle>) {
        adapter.updateDataSet(newDataSet)
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

    private fun menuAdd() {
        val action =
            MacrocycleListFragmentDirections.actionMacrocycleListFragmentToCreateMacrocycleFragment()
        findNavController().navigate(action)
    }
}
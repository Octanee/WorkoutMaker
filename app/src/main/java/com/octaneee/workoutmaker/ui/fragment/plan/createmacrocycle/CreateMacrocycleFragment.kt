package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Mesocycle
import com.octaneee.workoutmaker.data.model.entity.MesocycleType
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.logic.utility.TAG
import com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.adapter.CreateMacrocycleFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.viewmodel.CreateMacrocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_macrocycle.*

class CreateMacrocycleFragment : Fragment() {

    private val viewModel: CreateMacrocycleFragmentViewModel by viewModels()
    private val args: CreateMacrocycleFragmentArgs by navArgs()
    private lateinit var createMacrocycleFragmentDragDropAdapter: CreateMacrocycleFragmentDragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_create_macrocycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel.macrocycleWithMesocycles = args.macrocycleWithMesocycles

        fakeItems()
        setUpRecyclerView()
        setUpNewMesocycleFAB()
    }

    private fun setUpNewMesocycleFAB() {
        createMacrocycleFragmentNewMesocycleFAB.setOnClickListener {
            if (viewModel.macrocycleWithMesocycles == null) {
                findNavController().navigate(R.id.action_createMacrocycleFragment_to_createMesocycleFragment)
            } else {
                val action =
                    CreateMacrocycleFragmentDirections.actionCreateMacrocycleFragmentToCreateMesocycleFragment(
                        viewModel.macrocycleWithMesocycles!!
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun fakeItems() {
        for (i in 1..20) {
            val mesocycleAndMesocycleTypeWithMicrocycles = MesocycleAndMesocycleTypeWithMicrocycles(
                Mesocycle("Mesocycle Name $i", 1, 1),
                MesocycleType("Mesocycle Type $i"),
                null
            )
            viewModel.mesocycleAndMesocycleTypeWithMicrocyclesList.add(
                mesocycleAndMesocycleTypeWithMicrocycles
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_macrocycle_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createMacrocycleFragmentMenuSave -> menuSave()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerView() {
        createMacrocycleFragmentDragDropAdapter =
            CreateMacrocycleFragmentDragDropAdapter(viewModel.mesocycleAndMesocycleTypeWithMicrocyclesList)
        val recyclerView = createMacrocycleFragmentRecyclerView

        val onItemSwipeListener =
            object : OnItemSwipeListener<MesocycleAndMesocycleTypeWithMicrocycles> {
                override fun onItemSwiped(
                    position: Int,
                    direction: OnItemSwipeListener.SwipeDirection,
                    item: MesocycleAndMesocycleTypeWithMicrocycles
                ): Boolean {
                    Log.d(TAG, "Position: $position, Direction: $direction, Item: $item")
                    return when (direction) {
                        OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                            Toast.makeText(context, "Right to Left", Toast.LENGTH_SHORT).show()
                            false
                        }
                        OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                            Toast.makeText(context, "Left to Right", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            }

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = createMacrocycleFragmentDragDropAdapter
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener
            longPressToStartDragging = true
        }
    }
}
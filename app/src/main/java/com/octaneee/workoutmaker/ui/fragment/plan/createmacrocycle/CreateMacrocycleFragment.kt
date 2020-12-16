package com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Macrocycle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.adapter.CreateMacrocycleFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createmacrocycle.viewmodel.CreateMacrocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_macrocycle.view.*


class CreateMacrocycleFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: CreateMacrocycleFragmentViewModel by viewModels()
    private lateinit var adapter: CreateMacrocycleFragmentDragDropAdapter

    companion object {
        const val TAG = "CreateMacrocycleFrag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_macrocycle, container, false)

        setUpViewModel()
        setUpRecyclerView(view.createMacrocycleFragmentRecyclerView)
        setUpFAB(view.createMacrocycleFragmentNewMesocycleFAB)
        setUpEditText(view.createMacrocycleFragmentNameEditText)

        return view
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
        if (validateDate()) {
            mainActivityViewModel.macrocycleWithMesocycles = null
            viewModel.saveMacrocycle()
            val action =
                CreateMacrocycleFragmentDirections.actionCreateMacrocycleFragmentToMacrocycleListFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateDate(): Boolean {
        return checkName() //&& checkMesocycles()
    }

    private fun checkMesocycles(): Boolean {
        return if (viewModel.macrocycleWithMesocycles.mesocycles.isEmpty()) {
            Toast.makeText(context, "Add mesocycle", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkName(): Boolean {
        return if (viewModel.macrocycleWithMesocycles.macrocycle.macrocycleName.isEmpty()) {
            Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun setUpViewModel() {
        viewModel.macrocycleWithMesocycles =
            if (mainActivityViewModel.macrocycleWithMesocycles == null) {
                MacrocycleWithMesocycles(Macrocycle(""))
            } else {
                mainActivityViewModel.macrocycleWithMesocycles!!
            }
    }

    private fun setUpFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            mainActivityViewModel.macrocycleWithMesocycles = viewModel.macrocycleWithMesocycles
            val action =
                CreateMacrocycleFragmentDirections.actionCreateMacrocycleFragmentToCreateMesocycleFragment()
            findNavController().navigate(action)
        }
    }


    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter =
            CreateMacrocycleFragmentDragDropAdapter(viewModel.macrocycleWithMesocycles.mesocycles)

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CreateMacrocycleFragment.adapter
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            longPressToStartDragging = true
        }
    }

    private fun onItemSwipeListener() =
        object : OnItemSwipeListener<MesocycleAndMesocycleTypeWithMicrocycles> {
            override fun onItemSwiped(
                position: Int,
                direction: OnItemSwipeListener.SwipeDirection,
                item: MesocycleAndMesocycleTypeWithMicrocycles
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

    private fun swipeLeftToRight(item: MesocycleAndMesocycleTypeWithMicrocycles): Boolean {
        mainActivityViewModel.macrocycleWithMesocycles = viewModel.macrocycleWithMesocycles
        mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles = item
        val action =
            CreateMacrocycleFragmentDirections.actionCreateMacrocycleFragmentToCreateMesocycleFragment()
        findNavController().navigate(action)
        return true
    }

    private fun swipeRightToLeft(item: MesocycleAndMesocycleTypeWithMicrocycles): Boolean {
        viewModel.macrocycleWithMesocycles.mesocycles.remove(item)
        updateAdapterDateSet(viewModel.macrocycleWithMesocycles.mesocycles)
        return true
    }

    private fun updateAdapterDateSet(list: List<MesocycleAndMesocycleTypeWithMicrocycles>) {
        adapter.updateDataSet(list)
    }

    private fun setUpEditText(editText: EditText) {
        editText.setText(viewModel.macrocycleWithMesocycles.macrocycle.macrocycleName)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.macrocycleWithMesocycles.macrocycle.macrocycleName = "$sequence"
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }
}
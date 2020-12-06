package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSets
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.adapter.CreateMicrocycleFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.viewmodel.CreateMicrocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_microcycle.view.*


class CreateMicrocycleFragment : Fragment() {

    private val viewModel: CreateMicrocycleFragmentViewModel by viewModels()
    private val args: CreateMicrocycleFragmentArgs by navArgs()
    private lateinit var adapter: CreateMicrocycleFragmentDragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_microcycle, container, false)

        setUpFromArgs()
        setUpRecyclerView(view.createMicrocycleFragmentRecyclerView)
        setUpAddTrainingFAB(view.createMicrocycleFragmentAddTrainingFAB)
        setUpNumberOfDaysEditText(view.createMicrocycleFragmentNumberOfDaysEditText)
        return view
    }

    private fun setUpNumberOfDaysEditText(editText: EditText) {
        editText.setText(viewModel.microcycleWithTrainings.microcycle.numberOfDays.toString())

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (sequence != null) {
                    if (sequence.isNotEmpty()) {
                        viewModel.microcycleWithTrainings.microcycle.numberOfDays =
                            sequence.toString().toInt()
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_microcycle_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createMicrocycleFragmentMenuSave -> menuSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        if (validateData()) {
            val list =
                viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycles.toMutableList()
            list.add(viewModel.microcycleWithTrainings)
            viewModel.mesocycleAndMesocycleTypeWithMicrocycles.microcycles = list
            val action =
                CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateMesocycleFragment(
                    viewModel.macrocycleWithMesocycles,
                    viewModel.mesocycleAndMesocycleTypeWithMicrocycles,
                )
            findNavController().navigate(action)
        }
    }

    private fun validateData(): Boolean {
        return checkNumberOfDays() && checkTrainings()
    }

    private fun checkTrainings(): Boolean {
        return if (viewModel.microcycleWithTrainings.trainings.isEmpty()) {
            Toast.makeText(context, "Add training", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkNumberOfDays(): Boolean {
        return if (viewModel.microcycleWithTrainings.microcycle.numberOfDays <= 0) {
            Toast.makeText(context, "Enter number of days", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }

    }

    private fun setUpAddTrainingFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            val action =
                CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateTrainingFragment(
                    viewModel.macrocycleWithMesocycles,
                    viewModel.mesocycleAndMesocycleTypeWithMicrocycles,
                    viewModel.microcycleWithTrainings
                )
            findNavController().navigate(action)
        }
    }

    private fun setUpFromArgs() {
        args.macrocycleWithMesocycles.let {
            viewModel.macrocycleWithMesocycles = it
        }
        args.mesocycleAndMesocycleTypeWithMicrocycles.let {
            viewModel.mesocycleAndMesocycleTypeWithMicrocycles = it
            viewModel.microcycleWithTrainings.microcycle.mesocycleId = it.mesocycle.mesocycleId
        }
        args.microcycleWithTrainings?.let {
            viewModel.microcycleWithTrainings = it
        }
    }

    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter = CreateMicrocycleFragmentDragDropAdapter(
            viewModel.microcycleWithTrainings.trainings,
            viewModel
        )

        with(recyclerView) {
            adapter = this@CreateMicrocycleFragment.adapter
            layoutManager = LinearLayoutManager(context)
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            longPressToStartDragging = true
        }
    }

    private fun onItemSwipeListener() =
        object : OnItemSwipeListener<TrainingWithSets> {
            override fun onItemSwiped(
                position: Int,
                direction: OnItemSwipeListener.SwipeDirection,
                item: TrainingWithSets
            ): Boolean {
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
}
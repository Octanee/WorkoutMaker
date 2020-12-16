package com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.relation.MicrocycleWithTrainings
import com.octaneee.workoutmaker.data.model.relation.TrainingWithSetAndExercises
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.adapter.CreateMicrocycleFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createmicrocycle.viewmodel.CreateMicrocycleFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_microcycle.view.*


class CreateMicrocycleFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: CreateMicrocycleFragmentViewModel by viewModels()
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

        setUpViewModel()
        setUpRecyclerView(view.createMicrocycleFragmentRecyclerView)
        setUpFAB(view.createMicrocycleFragmentAddTrainingFAB)
        setUpEditText(view.createMicrocycleFragmentNumberOfDaysEditText)

        return view
    }

    private fun setUpEditText(editText: EditText) {
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
            mainActivityViewModel.mesocycleAndMesocycleTypeWithMicrocycles!!.microcycleWithTrainings.add(
                viewModel.microcycleWithTrainings
            )
            mainActivityViewModel.microcycleWithTrainings = null
            val action =
                CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateMesocycleFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateData(): Boolean {
        return checkNumberOfDays() && checkTrainings()
    }

    private fun checkTrainings(): Boolean {
        return if (viewModel.microcycleWithTrainings.trainingWithSetAndExercises.isEmpty()) {
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

    private fun setUpFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            mainActivityViewModel.microcycleWithTrainings = viewModel.microcycleWithTrainings
            val action =
                CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateTrainingFragment()
            findNavController().navigate(action)
        }
    }

    private fun setUpViewModel() {
        viewModel.microcycleWithTrainings =
            if (mainActivityViewModel.microcycleWithTrainings == null) {
                MicrocycleWithTrainings(Microcycle("", 0, 7))
            } else {
                mainActivityViewModel.microcycleWithTrainings!!
            }
    }

    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter = CreateMicrocycleFragmentDragDropAdapter(
            viewModel.microcycleWithTrainings.trainingWithSetAndExercises
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
        object : OnItemSwipeListener<TrainingWithSetAndExercises> {
            override fun onItemSwiped(
                position: Int,
                direction: OnItemSwipeListener.SwipeDirection,
                item: TrainingWithSetAndExercises
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

    private fun swipeLeftToRight(item: TrainingWithSetAndExercises): Boolean {
        mainActivityViewModel.microcycleWithTrainings = viewModel.microcycleWithTrainings
        mainActivityViewModel.trainingWithSetAndExercise = item
        val action =
            CreateMicrocycleFragmentDirections.actionCreateMicrocycleFragmentToCreateTrainingFragment()
        findNavController().navigate(action)
        return true
    }

    private fun swipeRightToLeft(item: TrainingWithSetAndExercises): Boolean {
        viewModel.microcycleWithTrainings.trainingWithSetAndExercises.remove(item)
        updateAdapterDataSet(viewModel.microcycleWithTrainings.trainingWithSetAndExercises)
        return false
    }

    private fun updateAdapterDataSet(newDataSet: List<TrainingWithSetAndExercises>) {
        adapter.updateDataSet(newDataSet)
    }
}
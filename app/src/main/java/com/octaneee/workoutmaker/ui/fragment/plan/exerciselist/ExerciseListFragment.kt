package com.octaneee.workoutmaker.ui.fragment.plan.exerciselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.octaneee.workoutmaker.data.model.relation.ExerciseHolder
import com.octaneee.workoutmaker.logic.utility.disableDragDirection
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselist.adapter.ExerciseListFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselist.viewmodel.ExerciseListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_exercise_list.view.*

class ExerciseListFragment : Fragment() {

    private val args: ExerciseListFragmentArgs by navArgs()
    private val viewModel: ExerciseListFragmentViewModel by viewModels()
    private lateinit var exerciseListFragmentDragDropAdapter: ExerciseListFragmentDragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_list, container, false)

        setUpCreateTrainingFragmentNewExerciseFAB(view.exerciseListFragmentNewExerciseFAB)
        setUpExerciseListFragmentExerciseListRecyclerView(view.exerciseListFragmentExerciseListRecyclerView)
        setUpExerciseList()

        viewModel.exerciseList.observe(viewLifecycleOwner, {
            exerciseListFragmentDragDropAdapter.updateDataSet(it)
        })

        return view
    }

    private fun setUpExerciseList() {
        args.equipment?.let {
            viewModel.setExerciseList(it)
            exerciseListFragmentDragDropAdapter.type = ExerciseListFragmentDragDropAdapter.EQUIPMENT
        }
        args.muscle?.let {
            viewModel.setExerciseList(it)
            exerciseListFragmentDragDropAdapter.type =
                ExerciseListFragmentDragDropAdapter.PRIMARY_MUSCLE
        }
        args.exerciseType?.let {
            viewModel.setExerciseList(it)
            exerciseListFragmentDragDropAdapter.type =
                ExerciseListFragmentDragDropAdapter.EXERCISE_TYPE
        }
    }

    private fun setUpCreateTrainingFragmentNewExerciseFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_exerciseListFragment_to_createExerciseFragment)
        }
    }

    private fun setUpExerciseListFragmentExerciseListRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        exerciseListFragmentDragDropAdapter =
            ExerciseListFragmentDragDropAdapter(
                listOf(),
            )

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = exerciseListFragmentDragDropAdapter
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            disableDragDirection()
        }
    }

    private fun onItemSwipeListener() = object : OnItemSwipeListener<ExerciseHolder> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: ExerciseHolder
        ): Boolean {
            return when (direction) {
                OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                    exerciseListRecyclerViewSwipeRightToLeft(item)
                    true
                }
                OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                    exerciseListRecyclerViewSwipeLeftToRight(item, position)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun exerciseListRecyclerViewSwipeRightToLeft(item: ExerciseHolder) {
        Toast.makeText(context, "Delete ${item.exercise.exerciseName}", Toast.LENGTH_SHORT).show()
        //viewModel.exerciseList.value?.remove(item)
        //viewModel.exerciseList.notifyObserver()
    }

    private fun exerciseListRecyclerViewSwipeLeftToRight(item: ExerciseHolder, position: Int) {
        Toast.makeText(context, "Edit ${item.exercise.exerciseName}", Toast.LENGTH_SHORT).show()
    }
}
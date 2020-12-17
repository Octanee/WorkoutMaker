package com.octaneee.workoutmaker.ui.fragment.plan.createtraining

import android.os.Bundle
import android.view.*
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
import com.octaneee.workoutmaker.data.model.relation.SetAndExercise
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import com.octaneee.workoutmaker.ui.fragment.plan.createtraining.adapter.CreateTrainingFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createtraining.viewmodel.CreateTrainingFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_training.view.*

class CreateTrainingFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()
    private val viewModel: CreateTrainingFragmentViewModel by viewModels()
    private lateinit var adapter: CreateTrainingFragmentDragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_training, container, false)

        setUpViewModel()
        setUpFAB(view.createTrainingFragmentAddExerciseToTrainingFAB)
        setUpRecyclerView(view.createTrainingFragmentRecyclerView)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_training_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createTrainingFragmentMenuSave -> menuSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        if (validateDate()) {
//            mainActivityViewModel.microcycleWithTrainings!!.trainingWithSetAndExercises.add(
//                viewModel.trainingWithSetAndExercises
//            )
//            mainActivityViewModel.trainingWithSetAndExercise = null
            val action =
                CreateTrainingFragmentDirections.actionCreateTrainingFragmentToCreateMicrocycleFragment()
            findNavController().navigate(action)
        }
    }

    private fun validateDate(): Boolean {
        return checkName() && checkDayOfMicrocycle() && checkSets()
    }

    private fun checkSets(): Boolean {
        return if (viewModel.trainingWithSetAndExercises.setAndExercises.isEmpty()) {
            Toast.makeText(context, "Add Set", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkDayOfMicrocycle(): Boolean {
        return if (viewModel.trainingWithSetAndExercises.training.dayOfMicrocycle <= 0) {
            Toast.makeText(context, "Enter day of microcycle", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkName(): Boolean {
        return if (viewModel.trainingWithSetAndExercises.training.trainingName.isEmpty()) {
            Toast.makeText(context, "Enter training name", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun setUpViewModel() {
//        viewModel.trainingWithSetAndExercises =
//            if (mainActivityViewModel.trainingWithSetAndExercise == null) {
//                TrainingWithSetAndExercises(Training("", 0, 7))
//            } else {
//                mainActivityViewModel.trainingWithSetAndExercise!!
//            }
    }

    private fun setUpFAB(fab: FloatingActionButton) {
        fab.setOnClickListener {
//            mainActivityViewModel.trainingWithSetAndExercise = viewModel.trainingWithSetAndExercises
            val action =
                CreateTrainingFragmentDirections.actionCreateTrainingFragmentToAddSetsToTrainingFragment()
            findNavController().navigate(action)
        }
    }

    private fun setUpRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter = CreateTrainingFragmentDragDropAdapter(
            viewModel.trainingWithSetAndExercises.setAndExercises,
        )

        with(recyclerView) {
            adapter = this@CreateTrainingFragment.adapter
            layoutManager = LinearLayoutManager(context)
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            longPressToStartDragging = true
        }
    }


    private fun onItemSwipeListener() = object : OnItemSwipeListener<SetAndExercise> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: SetAndExercise
        ): Boolean {
            return when (direction) {
                OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                    swipeListenerLeftToRight(item)
                }
                OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                    swipeListenerRightToLeft(item)
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun swipeListenerRightToLeft(item: SetAndExercise): Boolean {
        viewModel.trainingWithSetAndExercises.setAndExercises.remove(item)
        updateAdapterDataSet(viewModel.trainingWithSetAndExercises.setAndExercises)
        return true
    }

    private fun swipeListenerLeftToRight(item: SetAndExercise): Boolean {
//        mainActivityViewModel.trainingWithSetAndExercise = viewModel.trainingWithSetAndExercises
//        mainActivityViewModel.setAndExercisesList.add(item)
        val action =
            CreateTrainingFragmentDirections.actionCreateTrainingFragmentToAddSetsToTrainingFragment()
        findNavController().navigate(action)
        return true
    }

    private fun updateAdapterDataSet(newDataSet: List<SetAndExercise>) {
        adapter.updateDataSet(newDataSet)
    }
}
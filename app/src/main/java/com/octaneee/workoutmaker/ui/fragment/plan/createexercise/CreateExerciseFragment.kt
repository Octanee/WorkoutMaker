package com.octaneee.workoutmaker.ui.fragment.plan.createexercise

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ernestoyaquello.dragdropswiperecyclerview.DragDropSwipeRecyclerView
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemDragListener
import com.ernestoyaquello.dragdropswiperecyclerview.listener.OnItemSwipeListener
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.entity.Note
import com.octaneee.workoutmaker.logic.utility.notifyObserver
import com.octaneee.workoutmaker.ui.fragment.plan.createexercise.adapter.CreateExerciseFragmentDragDropAdapter
import com.octaneee.workoutmaker.ui.fragment.plan.createexercise.viewmodel.CreateExerciseFragmentViewModel
import kotlinx.android.synthetic.main.fragment_create_exercise.*
import kotlinx.android.synthetic.main.fragment_create_exercise.view.*

class CreateExerciseFragment : Fragment() {

    private val viewModel: CreateExerciseFragmentViewModel by viewModels()
    private lateinit var adapter: CreateExerciseFragmentDragDropAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_create_exercise, container, false)

        setUpNameEditText(view.createExerciseFragmentNameEditText)
        setUpPrimaryMuscleButton(view.createExerciseFragmentPrimaryMuscleButton)
        setUpAdditionalMusclesButton(view.createExerciseFragmentAdditionalMusclesButton)
        setUpExerciseTypeButton(view.createExerciseFragmentExerciseTypeButton)
        setUpEquipmentButton(view.createExerciseFragmentEquipmentButton)
        setUpListNoteRecyclerView(view.createExerciseFragmentNoteListRecyclerView)
        setUpAddNoteButton(view.createExerciseFragmentAddNoteButton)

        viewModel.noteList.observe(viewLifecycleOwner, {
            adapter.updateDataSet(it)
        })
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.create_exercise_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.createExerciseFragmentMenuSave -> menuSave()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuSave() {
        if (validation()) {
            viewModel.saveExercise(adapter.dataSet)
//            val action =
//                CreateExerciseFragmentDirections.actionCreateExerciseFragmentToExerciseListFragment(
//                    null,
//                    null,
//                    null
//                )
            findNavController().navigate(R.id.action_createExerciseFragment_to_exerciseListFragment)
        }
    }

    private fun validation(): Boolean {
        return checkName() && checkPrimaryMuscle() && checkEquipment() && checkExerciseType()
    }

    private fun checkName(): Boolean {
        return if (viewModel.exerciseName.isNullOrEmpty()) {
            Toast.makeText(context, "Please enter a name ", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun checkExerciseType(): Boolean {
        return if (viewModel.exerciseType != null) {
            true
        } else {
            Toast.makeText(context, "Please pick a exercise type", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun checkEquipment(): Boolean {
        return if (viewModel.equipment != null) {
            true
        } else {
            Toast.makeText(context, "Please pick a equipment", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun checkPrimaryMuscle(): Boolean {
        return if (viewModel.primaryMuscle != null) {
            true
        } else {
            Toast.makeText(context, "Please pick a primary muscle", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun setUpNameEditText(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                viewModel.exerciseName = "$sequence"
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

    private fun setUpPrimaryMuscleButton(button: Button) {
        viewModel.muscleList.observe(viewLifecycleOwner, { muscleList ->
            button.setOnClickListener {
                createExerciseFragmentPrimaryMuscleButtonOnClick(muscleList)
            }
        })
    }

    private fun createExerciseFragmentPrimaryMuscleButtonOnClick(muscleList: List<Muscle>) {
        val list = muscleList.toMutableList()

        if (viewModel.additionalMuscles.isNotEmpty()) {
            list.removeAll(viewModel.additionalMuscles)
        }

        val builder = AlertDialog.Builder(context)
        val names = mutableListOf<String>()
        for (muscle in list) {
            names.add(muscle.muscleName)
        }

        val checkedIndex =
            if (viewModel.primaryMuscle != null) list.indexOf(viewModel.primaryMuscle) else -1

        builder.apply {
            setTitle("Pick primary muscle group")
            setSingleChoiceItems(names.toTypedArray(), checkedIndex) { _, _ -> }
            setPositiveButton("Submit") { dialog, _ ->
                val position = (dialog as AlertDialog).listView.checkedItemPosition
                if (position != -1) {
                    val selectedItem = list[position]
                    viewModel.primaryMuscle = selectedItem
                    createExerciseFragmentPrimaryMuscleNameTextView.text =
                        selectedItem.muscleName
                }
            }
            setNegativeButton("Cancel", null)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = checkedIndex != -1
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = position != -1
            }
    }

    private fun setUpAdditionalMusclesButton(button: Button) {
        viewModel.muscleList.observe(viewLifecycleOwner, { muscleList ->
            button.setOnClickListener {
                createExerciseFragmentAdditionalMusclesButtonOnClick(muscleList)
            }
        })
    }

    private fun createExerciseFragmentAdditionalMusclesButtonOnClick(muscleList: List<Muscle>) {
        val list = muscleList.toMutableList()

        if (viewModel.primaryMuscle != null) {
            list.remove(viewModel.primaryMuscle)
        }

        val builder = AlertDialog.Builder(context)

        val names = mutableListOf<String>()
        val selectedList = mutableListOf<Boolean>()

        for (muscle in list) {
            names.add(muscle.muscleName)
            if (viewModel.additionalMuscles.contains(muscle)) {
                selectedList.add(true)
            } else {
                selectedList.add(false)
            }
        }

        val arrayNames = names.toTypedArray()
        val arrayChecked = selectedList.toBooleanArray()

        builder.apply {
            setTitle("Pick additional muscle groups")
            setMultiChoiceItems(
                arrayNames,
                arrayChecked
            ) { _, which, isChecked ->
                arrayChecked[which] = isChecked
            }

            setPositiveButton("Submit") { _, _ ->
                val selected = mutableListOf<Muscle>()
                for (i in arrayNames.indices) {
                    val checked = arrayChecked[i]
                    if (checked) {
                        selected.add(list[i])
                    }
                }
                viewModel.additionalMuscles = selected
                if (selected.size > 0) {
                    createExerciseFragmentAdditionalMusclesNumberTextView.text =
                        selected.size.toString()
                } else {
                    createExerciseFragmentAdditionalMusclesNumberTextView.text = ""
                }
            }

            setNegativeButton("Cancel", null)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun setUpExerciseTypeButton(button: Button) {
        viewModel.exerciseTypeList.observe(viewLifecycleOwner, { exerciseTypeList ->
            button.setOnClickListener {
                createExerciseFragmentExerciseTypeButtonOnClick(exerciseTypeList)
            }
        })
    }

    private fun createExerciseFragmentExerciseTypeButtonOnClick(exerciseTypeList: List<ExerciseType>) {
        val list = exerciseTypeList.toMutableList()
        val builder = AlertDialog.Builder(context)
        val names = mutableListOf<String>()
        for (exerciseType in list) {
            names.add(exerciseType.exerciseTypeName)
        }
        val checkedIndex =
            if (viewModel.exerciseType != null) list.indexOf(viewModel.exerciseType) else -1

        builder.apply {
            setTitle("Pick exercise type")
            setSingleChoiceItems(names.toTypedArray(), checkedIndex) { _, _ -> }
            setPositiveButton("Submit") { dialog, _ ->
                val position = (dialog as AlertDialog).listView.checkedItemPosition
                if (position != -1) {
                    val selectedItem = list[position]
                    viewModel.exerciseType = selectedItem
                    createExerciseFragmentExerciseTypeNameTextView.text =
                        selectedItem.exerciseTypeName
                }
            }
            setNegativeButton("Cancel", null)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = checkedIndex != -1
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = position != -1
            }
    }

    private fun setUpEquipmentButton(button: Button) {
        viewModel.equipmentList.observe(viewLifecycleOwner, { equipmentList ->
            button.setOnClickListener {
                createExerciseFragmentEquipmentButtonOnClick(equipmentList)
            }
        })
    }

    private fun createExerciseFragmentEquipmentButtonOnClick(equipmentList: List<Equipment>) {
        val list = equipmentList.toMutableList()
        val builder = AlertDialog.Builder(context)
        val names = mutableListOf<String>()
        for (equipment in list) {
            names.add(equipment.equipmentName)
        }
        val checkedIndex =
            if (viewModel.equipment != null) list.indexOf(viewModel.equipment) else -1

        builder.apply {
            setTitle("Pick equipment")
            setSingleChoiceItems(names.toTypedArray(), checkedIndex) { _, _ -> }
            setPositiveButton("Submit") { dialog, _ ->
                val position = (dialog as AlertDialog).listView.checkedItemPosition
                if (position != -1) {
                    val selectedItem = list[position]
                    viewModel.equipment = selectedItem
                    createExerciseFragmentEquipmentNameTextView.text =
                        selectedItem.equipmentName
                }
            }
            setNegativeButton("Cancel", null)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = checkedIndex != -1
        dialog.listView.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = position != -1
            }
    }

    private fun setUpAddNoteButton(button: Button) {
        button.setOnClickListener {
            createExerciseFragmentAddNoteButtonOnClick()
        }
    }

    private fun createExerciseFragmentAddNoteButtonOnClick() {
        val builder = AlertDialog.Builder(context)

        val view = layoutInflater.inflate(R.layout.dialog_add_note, null)
        val dialogAddNoteEditText: EditText = view.findViewById(R.id.dialogAddNoteEditText)

        builder.apply {
            setTitle("Add your note")
            setView(view)
            setCancelable(false)
            setPositiveButton("Add") { _, _ ->
                if (dialogAddNoteEditText.text.toString().isNotEmpty()) {
                    viewModel.noteList.value?.add(Note(dialogAddNoteEditText.text.toString()))
                    viewModel.noteList.notifyObserver()
                }
                createExerciseFragmentNestedScrollViewSmoothScrollToBottom()
            }
            setNegativeButton("Cancel", null)
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun setUpListNoteRecyclerView(recyclerView: DragDropSwipeRecyclerView) {
        adapter =
            CreateExerciseFragmentDragDropAdapter(viewModel.noteList.value!!.toList())

        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@CreateExerciseFragment.adapter
            orientation =
                DragDropSwipeRecyclerView.ListOrientation.VERTICAL_LIST_WITH_VERTICAL_DRAGGING
            swipeListener = onItemSwipeListener()
            dragListener = onItemDragListener()
            longPressToStartDragging = true
        }
    }

    private fun onItemDragListener() = object : OnItemDragListener<Note> {
        override fun onItemDragged(previousPosition: Int, newPosition: Int, item: Note) {
        }

        override fun onItemDropped(initialPosition: Int, finalPosition: Int, item: Note) {
            noteListRecyclerViewDrop(finalPosition, item)
        }
    }

    private fun noteListRecyclerViewDrop(finalPosition: Int, item: Note) {
        val list = viewModel.noteList.value!!
        list.remove(item)
        list.add(finalPosition, item)
        viewModel.noteList.value = list
        viewModel.noteList.notifyObserver()
    }

    private fun onItemSwipeListener() = object : OnItemSwipeListener<Note> {
        override fun onItemSwiped(
            position: Int,
            direction: OnItemSwipeListener.SwipeDirection,
            item: Note
        ): Boolean {
            return when (direction) {
                OnItemSwipeListener.SwipeDirection.RIGHT_TO_LEFT -> {
                    noteListRecyclerViewSwipeRightToLeft(item)
                    true
                }
                OnItemSwipeListener.SwipeDirection.LEFT_TO_RIGHT -> {
                    noteListRecyclerViewSwipeLeftToRight(item, position)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun noteListRecyclerViewSwipeRightToLeft(item: Note) {
        viewModel.noteList.value?.remove(item)
        viewModel.noteList.notifyObserver()
    }

    private fun noteListRecyclerViewSwipeLeftToRight(item: Note, position: Int) {
        val builder = AlertDialog.Builder(context)

        val view = layoutInflater.inflate(R.layout.dialog_add_note, null)
        val dialogAddNoteEditText: EditText =
            view.findViewById(R.id.dialogAddNoteEditText)
        dialogAddNoteEditText.setText(item.note)

        builder.apply {
            setTitle("Add your note")
            setView(view)
            setCancelable(false)
            setPositiveButton("Save") { _, _ ->
                if (dialogAddNoteEditText.text.toString().isNotEmpty()) {
                    viewModel.noteList.value!![position].note =
                        dialogAddNoteEditText.text.toString()
                } else {
                    viewModel.noteList.value?.remove(item)
                }
                viewModel.noteList.notifyObserver()
                createExerciseFragmentNestedScrollViewSmoothScrollToBottom()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun createExerciseFragmentNestedScrollViewSmoothScrollToBottom() {
        createExerciseFragmentNestedScrollView.smoothScrollTo(
            0, createExerciseFragmentNestedScrollView.getChildAt(0).height
        )
    }
}
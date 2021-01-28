package com.octaneee.workoutmaker.ui.exercise

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.adapter.NoteListAdapter
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.model.entity.ExerciseType
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.model.entity.Note
import com.octaneee.workoutmaker.ui.bottom_sheet.note.NoteBottomSheetFragment
import com.octaneee.workoutmaker.util.ItemListener
import com.octaneee.workoutmaker.util.ItemTouchHelperCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_exercise_filter.*
import kotlinx.android.synthetic.main.bottom_sheet_note.*
import kotlinx.android.synthetic.main.fragment_exercise.*

@AndroidEntryPoint
class ExerciseFragment : Fragment(R.layout.fragment_exercise), ItemListener,
    NoteBottomSheetFragment.NoteBottomSheetFragmentListener {

    private val args: ExerciseFragmentArgs by navArgs()
    private val viewModel: ExerciseViewModel by viewModels()

    private lateinit var adapter: NoteListAdapter
    private lateinit var noteBottomSheetFragment: NoteBottomSheetFragment

    private lateinit var muscleSpinner: Spinner
    private lateinit var equipmentSpinner: Spinner
    private lateinit var exerciseTypeSpinner: Spinner

    private lateinit var muscleSpinnerAdapter: ArrayAdapter<Muscle>
    private lateinit var equipmentSpinnerAdapter: ArrayAdapter<Equipment>
    private lateinit var exerciseTypeSpinnerAdapter: ArrayAdapter<ExerciseType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFromArgs()
        setupNoteBottomSheetDialogFragment()
        setupMuscleSpinner()
        setupExerciseTypeSpinner()
        setupEquipmentSpinner()
        setupNoteRecyclerView()
        setupAddNoteButton()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercise_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exerciseFragmentMenuEdit -> menuEdit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(position: Int) {
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
    }

    override fun onItemSwipedLeft(position: Int) {
        val item = viewModel.fullExercise.value!!.notes[position]
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.apply {
            setTitle("Delete?")
            setMessage("Are you sure want to delete this note?")
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
                viewModel.deleteNote(item)
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
        val item = viewModel.fullExercise.value!!.notes[position]
        viewModel.editedNote = item
        noteBottomSheetFragment.setup(item.note, true)
        noteBottomSheetFragment.show(
            requireActivity().supportFragmentManager,
            "NoteBottomSheetFragment"
        )
    }

    override fun onNoteBottomSheetFragmentSaveButtonClick(sequence: String, isEdit: Boolean) {
        if (isEdit) {
            if (!TextUtils.equals(viewModel.editedNote!!.note, sequence)) {
                if (!TextUtils.isEmpty(sequence)) {
                    val item = viewModel.editedNote!!
                    item.note = sequence
                    viewModel.updateNote(item)
                } else {
                    val item = viewModel.editedNote!!
                    viewModel.deleteNote(item)
                }
            }
            adapter.notifyItemChanged(viewModel.fullExercise.value!!.notes.indexOf(viewModel.editedNote))
        } else {
            if (!TextUtils.isEmpty(sequence)) {
                val item = Note(sequence)
                viewModel.insertNote(item, viewModel.fullExercise.value!!.exercise.exerciseId)
            }
        }
        noteBottomSheetFragment.dismiss()
    }

    override fun onNoteBottomSheetFragmentDismiss() {
        adapter.notifyDataSetChanged()
    }

    private fun menuEdit() {
        Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
    }

    private fun setupObservers() {
        viewModel.equipmentDataSet.observe(viewLifecycleOwner) {
            equipmentSpinnerAdapter.addAll(it)
            viewModel.fullExercise.value?.let { exercise ->
                equipmentSpinner.setSelection(it.indexOf(exercise.equipment))
            }
        }

        viewModel.muscleDataSet.observe(viewLifecycleOwner) {
            muscleSpinnerAdapter.addAll(it)
            viewModel.fullExercise.value?.let { exercise ->
                muscleSpinner.setSelection(it.indexOf(exercise.primaryMuscle))
            }
        }

        viewModel.exerciseTypeDataSet.observe(viewLifecycleOwner) {
            exerciseTypeSpinnerAdapter.addAll(it)
            viewModel.fullExercise.value?.let { exercise ->
                exerciseTypeSpinner.setSelection(it.indexOf(exercise.exerciseType))
            }
        }

        viewModel.fullExercise.observe(viewLifecycleOwner) {
            requireActivity().mainToolbar.title = it.exercise.exerciseName

            adapter.updateDataSet(it.notes)
            it.primaryMuscle?.let { primaryMuscle -> setupPrimaryMuscle(primaryMuscle) }
            it.exerciseType?.let { exerciseType -> setupExerciseType(exerciseType) }
            it.equipment?.let { equipment -> setupEquipment(equipment) }
        }
    }

    private fun setupNoteRecyclerView() {
        adapter = NoteListAdapter(this)

        val recyclerView = exerciseFragmentNoteRecyclerView
        recyclerView.apply {
            adapter = this@ExerciseFragment.adapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemTouchHelper = ItemTouchHelper(
            ItemTouchHelperCallback(
                adapter = adapter,
                dragDirs = 0,
                swipeDirs = ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            )
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setupEquipment(item: Equipment) {
        viewModel.equipmentDataSet.value?.let {
            equipmentSpinner.setSelection(it.indexOf(item))
        }
    }

    private fun setupExerciseType(item: ExerciseType) {
        viewModel.exerciseTypeDataSet.value?.let {
            exerciseTypeSpinner.setSelection(it.indexOf(item))
        }
    }

    private fun setupPrimaryMuscle(item: Muscle) {
        viewModel.muscleDataSet.value?.let {
            muscleSpinner.setSelection(it.indexOf(item))
        }
    }

    private fun setupFromArgs() {
        viewModel.setupFullExercise(args.exerciseId)
    }

    private fun setupAddNoteButton() {
        exerciseFragmentNoteAddButton.setOnClickListener {
            noteBottomSheetFragment.setup("")
            noteBottomSheetFragment.show(
                requireActivity().supportFragmentManager,
                "NoteBottomSheetFragment"
            )
        }
    }

    private fun setupNoteBottomSheetDialogFragment() {
        noteBottomSheetFragment = NoteBottomSheetFragment(this)
    }

    private fun setupEquipmentSpinner() {
        equipmentSpinner = exerciseFragmentEquipmentSpinner
        equipmentSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            mutableListOf()
        )
        equipmentSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        equipmentSpinner.adapter = equipmentSpinnerAdapter
        equipmentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = viewModel.equipmentDataSet.value!![position]
                viewModel.equipment = item
                viewModel.updateExercise()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupExerciseTypeSpinner() {
        exerciseTypeSpinner = exerciseFragmentExerciseTypeSpinner
        exerciseTypeSpinnerAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            mutableListOf()
        )
        exerciseTypeSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        exerciseTypeSpinner.adapter = exerciseTypeSpinnerAdapter
        exerciseTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = viewModel.exerciseTypeDataSet.value!![position]
                viewModel.exerciseType = item
                viewModel.updateExercise()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setupMuscleSpinner() {
        muscleSpinner = exerciseFragmentPrimaryMuscleSpinner
        muscleSpinnerAdapter =
            ArrayAdapter(
                requireContext(),
                R.layout.item_spinner,
                mutableListOf()
            )
        muscleSpinnerAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)

        muscleSpinner.adapter = muscleSpinnerAdapter
        muscleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = viewModel.muscleDataSet.value!![position]
                viewModel.muscle = item
                viewModel.updateExercise()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}
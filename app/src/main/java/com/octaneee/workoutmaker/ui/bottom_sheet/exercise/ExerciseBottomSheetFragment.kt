package com.octaneee.workoutmaker.ui.bottom_sheet.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_exercise.*

class ExerciseBottomSheetFragment(
    private val listener: ExerciseBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private val viewModel: ExerciseBottomSheetViewModel by viewModels()

    fun setup(name: String) {
        viewModel.exercisedName = name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupEditText()
    }

    private fun setupEditText() {
        val editText = bottomSheetExerciseNameEditText
        editText.setText(viewModel.exercisedName)
        editText.addTextChangedListener {
            viewModel.exercisedName = it?.toString() ?: ""
        }
    }

    private fun setupButton() {
        val button = bottomSheetExerciseSaveButton
        button.setOnClickListener {
            listener.onExerciseBottomSheetFragmentSaveButtonClick(viewModel.exercisedName)
        }
    }

    interface ExerciseBottomSheetFragmentListener {
        fun onExerciseBottomSheetFragmentSaveButtonClick(exerciseName: String)
    }
}
package com.octaneee.workoutmaker.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_microcycle.*

class MicrocycleBottomSheetFragment(
    private val listener: MicrocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private var microcycleName: String = ""
    private var microcycleNumberOfDays: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_microcycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupNameEditText()
        setupNumberOfDaysEditText()
    }

    private fun setupNumberOfDaysEditText() {
        bottomSheetMicrocycleNumberOfDaysEditText.setText(microcycleNumberOfDays)
        bottomSheetMicrocycleNumberOfDaysEditText.addTextChangedListener {
            listener.onMicrocycleBottomSheetFragmentNumberOfDaysEditTextChange(it.toString())
        }
    }

    private fun setupNameEditText() {
        bottomSheetMicrocycleNameEditText.setText(microcycleName)
        bottomSheetMicrocycleNameEditText.addTextChangedListener {
            listener.onMicrocycleBottomSheetFragmentNameEditTextChange(it.toString())
        }
    }

    private fun setupButton() {
        bottomSheetMicrocycleSaveButton.setOnClickListener {
            listener.onMicrocycleBottomSheetFragmentSaveButtonClick()
        }
    }

    fun setup(name: String, numberOfDays: String) {
        microcycleName = name
        microcycleNumberOfDays = numberOfDays
    }

    interface MicrocycleBottomSheetFragmentListener {
        fun onMicrocycleBottomSheetFragmentSaveButtonClick()
        fun onMicrocycleBottomSheetFragmentNameEditTextChange(sequence: String?)
        fun onMicrocycleBottomSheetFragmentNumberOfDaysEditTextChange(sequence: String?)
    }
}
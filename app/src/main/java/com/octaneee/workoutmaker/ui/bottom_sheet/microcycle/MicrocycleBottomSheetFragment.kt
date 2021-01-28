package com.octaneee.workoutmaker.ui.bottom_sheet.microcycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_microcycle.*

class MicrocycleBottomSheetFragment(
    private val listener: MicrocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private val viewModel: MicrocycleBottomSheetViewModel by viewModels()

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
        bottomSheetMicrocycleNumberOfDaysEditText.setText(viewModel.numberOfDays.toString())
        bottomSheetMicrocycleNumberOfDaysEditText.addTextChangedListener {
            viewModel.numberOfDays = if (!it?.toString().isNullOrEmpty()) {
                it.toString().toInt()
            } else {
                1
            }

        }
    }

    private fun setupNameEditText() {
        bottomSheetMicrocycleNameEditText.setText(viewModel.name)
        bottomSheetMicrocycleNameEditText.addTextChangedListener {
            viewModel.name = it?.toString() ?: ""
        }
    }

    private fun setupButton() {
        bottomSheetMicrocycleSaveButton.setOnClickListener {
            listener.onMicrocycleBottomSheetFragmentSaveButtonClick(
                viewModel.name,
                viewModel.numberOfDays
            )
        }
    }

    fun setup(microcycleName: String, microcycleNumberOfDays: Int) {
        viewModel.name = microcycleName
        viewModel.numberOfDays = microcycleNumberOfDays
    }

    interface MicrocycleBottomSheetFragmentListener {
        fun onMicrocycleBottomSheetFragmentSaveButtonClick(
            microcycleName: String,
            microcycleNumberOfDays: Int
        )
    }
}
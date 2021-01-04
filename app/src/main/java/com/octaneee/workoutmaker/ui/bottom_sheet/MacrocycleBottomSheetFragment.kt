package com.octaneee.workoutmaker.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_macrocycle.*

class MacrocycleBottomSheetFragment(
    private val listener: MacrocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private var macrocycleName: String = ""

    fun setup(name: String) {
        macrocycleName = name
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet_macrocycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetMacrocycleSaveButton.setOnClickListener {
            listener.onMacrocycleBottomSheetFragmentSaveButtonClick()
        }

        bottomSheetMacrocycleNameEditText.setText(macrocycleName)

        bottomSheetMacrocycleNameEditText.addTextChangedListener {
            listener.onMacrocycleBottomSheetFragmentNameEditTextChange(it.toString())
        }
    }

    interface MacrocycleBottomSheetFragmentListener {
        fun onMacrocycleBottomSheetFragmentSaveButtonClick()
        fun onMacrocycleBottomSheetFragmentNameEditTextChange(sequence: String?)
    }
}
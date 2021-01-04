package com.octaneee.workoutmaker.ui.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.MesocycleType
import kotlinx.android.synthetic.main.bottom_sheet_mesocycle.*

class MesocycleBottomSheetFragment(
    private val listener: MesocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private var mesocycleTypeDataSet: List<MesocycleType> = listOf()
    private var mesocycleName: String = ""
    private var mesocycleTypeIndex: Int = 0

    private lateinit var adapter: ArrayAdapter<MesocycleType>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet_mesocycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupEditText()
        setupSpinner()
    }

    fun setup(name: String, typeIndex: Int) {
        mesocycleName = name
        mesocycleTypeIndex = typeIndex
    }

    private fun setupButton() {
        bottomSheetMesocycleSaveButton.setOnClickListener {
            listener.onMesocycleBottomSheetFragmentSaveButtonClick()
        }
    }

    private fun setupSpinner() {
        val spinner = bottomSheetMesocycleSpinner
        adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            mesocycleTypeDataSet
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner.adapter = adapter

        spinner.setSelection(mesocycleTypeIndex)

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val item = mesocycleTypeDataSet[position]
                    listener.onMesocycleBottomSheetFragmentSpinnerItemSelected(item)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun setupEditText() {
        bottomSheetMesocycleNameEditText.setText(mesocycleName)

        bottomSheetMesocycleNameEditText.addTextChangedListener {
            listener.onMesocycleBottomSheetFragmentNameEditTextChange(it.toString())
        }
    }

    fun updateMesocycleTypes(newDataSet: List<MesocycleType>) {
        mesocycleTypeDataSet = newDataSet
    }

    interface MesocycleBottomSheetFragmentListener {
        fun onMesocycleBottomSheetFragmentSaveButtonClick()
        fun onMesocycleBottomSheetFragmentNameEditTextChange(sequence: String?)
        fun onMesocycleBottomSheetFragmentSpinnerItemSelected(item: MesocycleType)
    }
}
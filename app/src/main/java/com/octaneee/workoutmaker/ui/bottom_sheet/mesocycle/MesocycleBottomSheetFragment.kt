package com.octaneee.workoutmaker.ui.bottom_sheet.mesocycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.MesocycleType
import kotlinx.android.synthetic.main.bottom_sheet_mesocycle.*

class MesocycleBottomSheetFragment(
    private val listener: MesocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private val viewModel: MesocycleBottomSheetViewModel by viewModels()

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

    fun setup(mesocycleName: String, mesocycleTypeIndex: Int) {
        viewModel.name = mesocycleName
        viewModel.index = mesocycleTypeIndex
    }

    private fun setupButton() {
        bottomSheetMesocycleSaveButton.setOnClickListener {
            listener.onMesocycleBottomSheetFragmentSaveButtonClick(
                viewModel.name,
                viewModel.dataSet[viewModel.index]
            )
        }
    }

    private fun setupSpinner() {
        val spinner = bottomSheetMesocycleSpinner
        adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            viewModel.dataSet
        )
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner.adapter = adapter

        spinner.setSelection(viewModel.index)

        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.index = position
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun setupEditText() {
        bottomSheetMesocycleNameEditText.setText(viewModel.name)

        bottomSheetMesocycleNameEditText.addTextChangedListener {
            viewModel.name = it?.toString() ?: ""
        }
    }

    fun updateMesocycleTypes(newDataSet: List<MesocycleType>) {
        viewModel.dataSet = newDataSet
    }

    interface MesocycleBottomSheetFragmentListener {
        fun onMesocycleBottomSheetFragmentSaveButtonClick(
            mesocycleName: String,
            mesocycleType: MesocycleType
        )
    }
}
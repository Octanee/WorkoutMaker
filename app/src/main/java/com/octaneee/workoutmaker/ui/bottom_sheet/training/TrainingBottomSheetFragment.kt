package com.octaneee.workoutmaker.ui.bottom_sheet.training

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
import kotlinx.android.synthetic.main.bottom_sheet_training.*

class TrainingBottomSheetFragment(private val listener: TrainingBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private val viewModel: TrainingBottomSheetViewModel by viewModels()

    private lateinit var adapter: ArrayAdapter<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButton()
        setupNameEditText()
        setupSpinner()
    }

    private fun setupNameEditText() {
        bottomSheetTrainingNameEditText.setText(viewModel.name)

        bottomSheetTrainingNameEditText.addTextChangedListener {
            viewModel.name = it?.toString() ?: ""
        }
    }

    private fun setupButton() {
        bottomSheetTrainingSaveButton.setOnClickListener {
            listener.onTrainingBottomSheetFragmentSaveButtonClick(
                viewModel.name,
                viewModel.numberOfDay
            )
        }
    }

    private fun setupSpinner() {
        val spinner = bottomSheetTrainingNumberOfDaySpinner
        adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, viewModel.numberOfDays)
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner.adapter = adapter

        spinner.setSelection(viewModel.numberOfDay - 1)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.numberOfDay = position + 1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun setup(name: String, numberOfDay: Int) {
        viewModel.name = name
        viewModel.numberOfDay = numberOfDay
    }

    fun setNumberOfDays(number: Int) {
        for (day in 1..number) {
            viewModel.numberOfDays.add(day)
        }
    }

    interface TrainingBottomSheetFragmentListener {
        fun onTrainingBottomSheetFragmentSaveButtonClick(
            trainingName: String,
            trainingNumberOfDay: Int
        )
    }
}
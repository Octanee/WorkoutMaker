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
import kotlinx.android.synthetic.main.bottom_sheet_training.*

class TrainingBottomSheetFragment(private val listener: TrainingBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private var trainingName: String = ""
    private var trainingNumberOfDay: Int = 1
    private var numberOfDays = mutableListOf<Int>()

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
        bottomSheetTrainingNameEditText.setText(trainingName)

        bottomSheetTrainingNameEditText.addTextChangedListener {
            listener.onTrainingBottomSheetFragmentNameEditTextChange(it.toString())
        }
    }

    private fun setupButton() {
        bottomSheetTrainingSaveButton.setOnClickListener {
            listener.onTrainingBottomSheetFragmentSaveButtonClick()
        }
    }

    private fun setupSpinner() {
        val spinner = bottomSheetTrainingNumberOfDaySpinner
        adapter = ArrayAdapter(requireContext(), R.layout.item_spinner, numberOfDays)
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        spinner.adapter = adapter

        spinner.setSelection(trainingNumberOfDay - 1)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = numberOfDays[position]
                listener.onTrainingBottomSheetFragmentNumberOfDaySpinnerItemSelected(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    fun setup(name: String, numberOfDay: Int) {
        trainingName = name
        trainingNumberOfDay = numberOfDay
    }

    fun setNumberOfDays(number: Int) {
        for (day in 1..number) {
            numberOfDays.add(day)
        }
    }

    interface TrainingBottomSheetFragmentListener {
        fun onTrainingBottomSheetFragmentSaveButtonClick()
        fun onTrainingBottomSheetFragmentNameEditTextChange(sequence: String?)
        fun onTrainingBottomSheetFragmentNumberOfDaySpinnerItemSelected(item: Int)
    }
}
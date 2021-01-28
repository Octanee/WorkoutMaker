package com.octaneee.workoutmaker.ui.bottom_sheet.macrocycle

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_macrocycle.*
import java.text.SimpleDateFormat
import java.util.*

class MacrocycleBottomSheetFragment(
    private val listener: MacrocycleBottomSheetFragmentListener
) : BottomSheetDialogFragment() {

    private val viewModel: MacrocycleBottomSheetViewModel by viewModels()

    private val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottom_sheet_macrocycle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSave()
        setupName()
        setupStartDate()
        setupEndDate()
    }

    fun setup(macrocycleName: String, macrocycleStartDate: Date, macrocycleEndDate: Date?) {
        viewModel.name = macrocycleName
        viewModel.startDate = macrocycleStartDate
        viewModel.endDate = macrocycleEndDate
    }

    private fun setupEndDate() {
        viewModel.endDate?.let {
            bottomSheetMacrocycleEndDateTextView.text = sdf.format(it)
        }

        bottomSheetMacrocycleEndDateButton.setOnClickListener {
            setupEndDateDialog()
        }
    }

    private fun setupEndDateDialog() {
        val endCalendar = Calendar.getInstance()
        viewModel.endDate?.let {
            endCalendar.time = it
        }
        val endYear = endCalendar.get(Calendar.YEAR)
        val endMonth = endCalendar.get(Calendar.MONTH)
        val endDay = endCalendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            viewModel.endDate = calendar.time

            val endDateText = if (viewModel.endDate!!.after(viewModel.startDate)) {
                sdf.format(viewModel.endDate!!)
            } else {
                viewModel.endDate = null
                "Error"
            }

            bottomSheetMacrocycleEndDateTextView.text = endDateText

        }

        val datePickerDialog =
            DatePickerDialog(requireContext(), listener, endYear, endMonth, endDay)
        datePickerDialog.show()
    }

    private fun setupStartDate() {
        bottomSheetMacrocycleStartDateTextView.text = sdf.format(viewModel.startDate)

        bottomSheetMacrocycleStartDateButton.setOnClickListener {
            setupStartDateDialog()
        }
    }

    private fun setupStartDateDialog() {
        val startCalendar = Calendar.getInstance()
        startCalendar.time = viewModel.startDate
        val startYear = startCalendar.get(Calendar.YEAR)
        val startMonth = startCalendar.get(Calendar.MONTH)
        val startDay = startCalendar.get(Calendar.DAY_OF_MONTH)

        val listener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, day)
            viewModel.startDate = calendar.time
            bottomSheetMacrocycleStartDateTextView.text = sdf.format(viewModel.startDate)
        }
        val datePickerDialog =
            DatePickerDialog(requireContext(), listener, startYear, startMonth, startDay)
        datePickerDialog.show()
    }

    private fun setupName() {
        bottomSheetMacrocycleNameEditText.setText(viewModel.name)

        bottomSheetMacrocycleNameEditText.addTextChangedListener {
            viewModel.name = it?.toString() ?: ""
        }
    }

    private fun setupSave() {
        bottomSheetMacrocycleSaveButton.setOnClickListener {
            listener.onMacrocycleBottomSheetFragmentSaveButtonClick(
                viewModel.name,
                viewModel.startDate,
                viewModel.endDate
            )
        }
    }

    interface MacrocycleBottomSheetFragmentListener {
        fun onMacrocycleBottomSheetFragmentSaveButtonClick(
            macrocycleName: String,
            macrocycleStartDate: Date,
            macrocycleEndDate: Date?
        )
    }
}
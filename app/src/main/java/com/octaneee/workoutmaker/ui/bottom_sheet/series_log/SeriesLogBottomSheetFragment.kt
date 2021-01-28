package com.octaneee.workoutmaker.ui.bottom_sheet.series_log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.Series
import com.octaneee.workoutmaker.model.entity.SeriesLog
import kotlinx.android.synthetic.main.bottom_sheet_series_log.*
import java.util.*

class SeriesLogBottomSheetFragment(private val listener: SeriesLogBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private val viewModel: SeriesLogBottomSheetViewModel by viewModels()
    private lateinit var currentSeries: Series

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_series_log, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.series.value = currentSeries

        viewModel.series.observe(viewLifecycleOwner) {
            it?.let {
                viewModel.reps = it.maximum ?: it.minimum
                viewModel.weight = it.weight
                viewModel.repsInReserve = it.repsInReserve
            }

            setupReps()
            setupRepsInReserve()
            setupWeight()
        }

        setupLogButton()
    }

    fun setupSeries(series: Series) {
        currentSeries = series
    }

    private fun setupLogButton() {
        bottomSheetSeriesLogButton.setOnClickListener {
            listener.onSeriesBottomSheetFragmentLogButtonClick(
                SeriesLog(
                    viewModel.series.value!!.seriesId,
                    viewModel.reps ?: 0,
                    viewModel.repsInReserve ?: 0,
                    viewModel.weight ?: 0f,
                    Calendar.getInstance().time
                )
            )
        }
    }

    private fun setupRepsInReserve() {
        val editText = bottomSheetSeriesLogRepsInReserveEditText
        editText.setText(viewModel.repsInReserve?.toString() ?: "")

        editText.addTextChangedListener {
            viewModel.repsInReserve = if (!it?.toString().isNullOrEmpty()) {
                it.toString().toInt()
            } else {
                null
            }
        }
    }

    private fun setupWeight() {
        val editText = bottomSheetSeriesLogWeightEditText
        editText.setText(viewModel.weight?.toString() ?: "")

        editText.addTextChangedListener {
            viewModel.weight = if (!it?.toString().isNullOrEmpty()) {
                it.toString().toFloat()
            } else {
                null
            }
        }
    }

    private fun setupReps() {
        val editText = bottomSheetSeriesLogRepsEditText
        editText.setText(viewModel.reps?.toString() ?: "")

        editText.addTextChangedListener {
            viewModel.reps = if (!it?.toString().isNullOrEmpty()) {
                it.toString().toInt()
            } else {
                null
            }
        }
    }

    interface SeriesLogBottomSheetFragmentListener {
        fun onSeriesBottomSheetFragmentLogButtonClick(log: SeriesLog)
    }
}
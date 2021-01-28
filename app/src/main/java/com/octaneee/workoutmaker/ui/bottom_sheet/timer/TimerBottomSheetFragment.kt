package com.octaneee.workoutmaker.ui.bottom_sheet.timer

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.octaneee.workoutmaker.R
import kotlinx.android.synthetic.main.bottom_sheet_timer.*

class TimerBottomSheetFragment(private val listener: TimerBottomSheetFragmentListener) :
    BottomSheetDialogFragment() {

    private val viewModel: TimerBottomSheetViewModel by viewModels()
    private var time: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.timer = object : CountDownTimer(time * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                bottomSheetTimerRestTimeTextView.text =
                    ((millisUntilFinished / 1000) + 1).toString()
            }

            override fun onFinish() {
                listener.onTimerStop()
            }
        }

        viewModel.timer.start()

        setupStop()
    }

    private fun setupStop() {
        bottomSheetTimerStopButton.setOnClickListener {
            viewModel.timer.cancel()
            listener.onTimerStop()
        }
    }

    fun setup(time: Int) {
        this.time = time
    }

    interface TimerBottomSheetFragmentListener {
        fun onTimerStop()
    }
}
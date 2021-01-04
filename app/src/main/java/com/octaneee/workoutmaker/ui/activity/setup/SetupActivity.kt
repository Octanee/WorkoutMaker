package com.octaneee.workoutmaker.ui.activity.setup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.octaneee.workoutmaker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)


    }
}
package com.octaneee.workoutmaker.ui.activity.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.ui.activity.main.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationView()

        //viewModel.populateDatabase(applicationContext)

        //observeTrainingWithSetAndExercises()
        //observeMacrocycles()
    }

    private fun observeMacrocycles() {
        Log.d(TAG, "observeMacrocycles: Start")
        viewModel.macrocycles.observe(this, {
            Log.d(TAG, "observeMacrocycles: Observe")
            for (macrocycle in it) {
                Log.d(TAG, macrocycle.toString())
            }
        })
        Log.d(TAG, "observeMacrocycles: End")
    }

    private fun observeTrainingWithSetAndExercises() {
        viewModel.trainingWithSetAndExercisesList.observe(this, {
            for (training in it) {
                Log.d(TAG, training.toString())
            }
        })
    }


    private fun setupBottomNavigationView() {
        val navController = findNavController(R.id.navHostFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.planFragment,
                R.id.bodyFragment,
                R.id.workoutFragment,
                R.id.statsFragment,
                R.id.settingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navHostFragment).navigateUp()
    }
}
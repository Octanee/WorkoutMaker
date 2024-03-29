package com.octaneee.workoutmaker.ui.current_workout.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.service.WorkoutService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_workout.*

@AndroidEntryPoint
class CurrentWorkoutActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.workoutNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(workoutToolbar)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.currentWorkoutExerciseListFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        startWorkoutService()
    }

    private fun startWorkoutService() {
        val intent = Intent(applicationContext, WorkoutService::class.java)
        startService(intent)
    }

    fun stopWorkoutService() {
        val intent = Intent(applicationContext, WorkoutService::class.java)
        stopService(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}
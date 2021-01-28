package com.octaneee.workoutmaker.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.other.Constant.WORKOUT_SERVICE_CHANNEL_ID
import com.octaneee.workoutmaker.other.Constant.WORKOUT_SERVICE_FOREGROUND_ID
import com.octaneee.workoutmaker.ui.current_workout.activity.CurrentWorkoutActivity
import com.octaneee.workoutmaker.util.WorkoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WorkoutService : Service() {

    @Inject
    lateinit var workoutManager: WorkoutManager

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (!workoutManager.isWorking) {
            workoutManager.startWorkout()

            val pendingIntent: PendingIntent =
                Intent(this, CurrentWorkoutActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(this, 0, notificationIntent, 0)
                }

            val notification: Notification = Notification.Builder(this, WORKOUT_SERVICE_CHANNEL_ID)
                .setContentTitle(getText(R.string.app_name))
                .setContentText(workoutManager.training.value!!.trainingName)
                .setSmallIcon(R.drawable.ic_gym)
                .setContentIntent(pendingIntent)
                .build()

            startForeground(WORKOUT_SERVICE_FOREGROUND_ID, notification)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        workoutManager.stopWorkout()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
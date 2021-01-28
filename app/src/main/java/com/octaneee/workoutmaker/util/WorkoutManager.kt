package com.octaneee.workoutmaker.util

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import com.octaneee.workoutmaker.model.WorkoutExercise
import com.octaneee.workoutmaker.model.WorkoutSeries
import com.octaneee.workoutmaker.model.entity.SeriesLog
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.relations.TrainingWithExercises
import com.octaneee.workoutmaker.other.extension.addAll

class WorkoutManager {

    var startTime: MutableLiveData<Long> = MutableLiveData(0)
    var isWorking: Boolean = false

    var training = MutableLiveData<Training>()

    var currentExercise: WorkoutExercise? = null
    var currentSeries: WorkoutSeries? = null

    val exercises = MutableLiveData<MutableList<WorkoutExercise>>()
    val logs = mutableListOf<SeriesLog>()

    fun setTrainingWithExercises(trainingWithExercises: TrainingWithExercises) {
        training.value = trainingWithExercises.training

        val tempExercises = mutableListOf<WorkoutExercise>()
        for ((trainingExerciseCrossRef, exercise, seriesList) in trainingWithExercises.exerciseWithSeries) {
            val crossRefId = trainingExerciseCrossRef.trainingExerciseCrossRefId
            var item =
                exercises.value?.firstOrNull { workoutExercise -> workoutExercise.trainingExerciseCrossRefId == crossRefId }
            if (item == null) {
                val series = mutableListOf<WorkoutSeries>()
                seriesList.forEach {
                    series.add(WorkoutSeries(it))
                }
                item = WorkoutExercise(crossRefId, exercise, series)
            }
            tempExercises.add(item)
        }
        exercises.addAll(tempExercises)
    }

    fun setCurrentExercise(position: Int) {
        currentExercise = exercises.value!![position]
        currentSeries =
            currentExercise!!.series.firstOrNull { workoutSeries -> !workoutSeries.isCompleted }
    }

    fun nextSeries() {
        val nextIndex = currentExercise!!.series.indexOf(currentSeries) + 1
        currentSeries = if (currentExercise!!.series.size > nextIndex) {
            currentExercise!!.series[nextIndex]
        } else {
            currentExercise = null
            null
        }
    }

    fun startWorkout() {
        isWorking = true
        startTime.value = SystemClock.elapsedRealtime()
    }

    fun stopWorkout() {
        isWorking = false

        //val workoutTime = SystemClock.elapsedRealtime() - startTime.value!!
    }
}
package com.octaneee.workoutmaker.data.database

import com.octaneee.workoutmaker.data.dao.*
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao

interface WorkoutMakerDatabaseDao {

    fun getEquipmentDao(): EquipmentDao
    fun getExerciseDao(): ExerciseDao
    fun getExerciseTypeDao(): ExerciseTypeDao
    fun getMacrocycleDao(): MacrocycleDao
    fun getMesocycleDao(): MesocycleDao
    fun getMesocycleTypeDao(): MesocycleTypeDao
    fun getMicrocycleDao(): MicrocycleDao
    fun getMuscleDao(): MuscleDao
    fun getNoteDao(): NoteDao
    fun getSetDao(): SetDao
    fun getSetLogDao(): SetLogDao
    fun getSetTypeDao(): SetTypeDao
    fun getTrainingDao(): TrainingDao
    fun getUserDao(): UserDao
    fun getExerciseMuscleCrossRefDao(): ExerciseMuscleCrossRefDao
    fun getExerciseNoteCrossRefDao(): ExerciseNoteCrossRefDao
}
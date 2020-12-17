package com.octaneee.workoutmaker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.octaneee.workoutmaker.data.converter.Converter
import com.octaneee.workoutmaker.data.dao.*
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef

@Database(
    entities = [
        Equipment::class,
        Exercise::class,
        ExerciseType::class,
        Macrocycle::class,
        Measurement::class,
        Mesocycle::class,
        MesocycleType::class,
        Microcycle::class,
        Muscle::class,
        Note::class,
        Set::class,
        SetLog::class,
        SetType::class,
        Training::class,
        User::class,
        ExerciseMuscleCrossRef::class,
        ExerciseNoteCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class WorkoutMakerDatabase : RoomDatabase() {

    abstract fun getExerciseMuscleCrossRefDao(): ExerciseMuscleCrossRefDao
    abstract fun getExerciseNoteCrossRefDao(): ExerciseNoteCrossRefDao

    abstract fun getEquipmentDao(): EquipmentDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getExerciseTypeDao(): ExerciseTypeDao
    abstract fun getMacrocycleDao(): MacrocycleDao
    abstract fun getMeasurementDao(): MeasurementDao
    abstract fun getMesocycleDao(): MesocycleDao
    abstract fun getMesocycleTypeDao(): MesocycleTypeDao
    abstract fun getMicrocycleDao(): MicrocycleDao
    abstract fun getMuscleDao(): MuscleDao
    abstract fun getNoteDao(): NoteDao
    abstract fun getSetDao(): SetDao
    abstract fun getSetLogDao(): SetLogDao
    abstract fun getSetTypeDao(): SetTypeDao
    abstract fun getTrainingDao(): TrainingDao
    abstract fun getUserDao(): UserDao
}
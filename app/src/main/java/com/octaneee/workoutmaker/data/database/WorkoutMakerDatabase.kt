package com.octaneee.workoutmaker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.octaneee.workoutmaker.data.converter.Converter
import com.octaneee.workoutmaker.data.dao.*
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseMuscleCrossRefDao
import com.octaneee.workoutmaker.data.dao.crossref.ExerciseNoteCrossRefDao
import com.octaneee.workoutmaker.data.dao.crossref.TrainingExerciseCrossRefDao
import com.octaneee.workoutmaker.model.entity.*
import com.octaneee.workoutmaker.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef

@Database(
    entities = [
        BodyMeasurement::class,
        Equipment::class,
        Exercise::class,
        ExerciseType::class,
        Macrocycle::class,
        Mesocycle::class,
        MesocycleType::class,
        Microcycle::class,
        Muscle::class,
        Note::class,
        Series::class,
        SeriesLog::class,
        SeriesType::class,
        Training::class,
        ExerciseMuscleCrossRef::class,
        ExerciseNoteCrossRef::class,
        TrainingExerciseCrossRef::class,
        WeightMeasurement::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
abstract class WorkoutMakerDatabase : RoomDatabase() {

    abstract fun getExerciseMuscleCrossRefDao(): ExerciseMuscleCrossRefDao
    abstract fun getExerciseNoteCrossRefDao(): ExerciseNoteCrossRefDao
    abstract fun getTrainingExerciseCrossRefDao(): TrainingExerciseCrossRefDao

    abstract fun getBodyMeasurementDao(): BodyMeasurementDao
    abstract fun getEquipmentDao(): EquipmentDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getExerciseTypeDao(): ExerciseTypeDao
    abstract fun getMacrocycleDao(): MacrocycleDao
    abstract fun getMesocycleDao(): MesocycleDao
    abstract fun getMesocycleTypeDao(): MesocycleTypeDao
    abstract fun getMicrocycleDao(): MicrocycleDao
    abstract fun getMuscleDao(): MuscleDao
    abstract fun getNoteDao(): NoteDao
    abstract fun getSetDao(): SetDao
    abstract fun getSetLogDao(): SetLogDao
    abstract fun getSetTypeDao(): SetTypeDao
    abstract fun getTrainingDao(): TrainingDao
    abstract fun getWeightMeasurementDao(): WeightMeasurementDao
}
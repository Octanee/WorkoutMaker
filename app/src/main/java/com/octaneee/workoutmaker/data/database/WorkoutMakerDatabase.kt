package com.octaneee.workoutmaker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.octaneee.workoutmaker.data.converter.Converter
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.logic.utility.Constant

@Database(
    entities = [
        Equipment::class,
        Exercise::class,
        ExerciseType::class,
        Macrocycle::class,
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
    version = 16,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class WorkoutMakerDatabase : RoomDatabase(), WorkoutMakerDatabaseDao {

    companion object {
        @Volatile
        private var instance: WorkoutMakerDatabase? = null

        fun getDatabase(context: Context): WorkoutMakerDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WorkoutMakerDatabase {
            return Room.databaseBuilder(
                context,
                WorkoutMakerDatabase::class.java,
                Constant.DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
package com.octaneee.workoutmaker.di

import android.content.Context
import androidx.room.Room
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.other.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWorkoutMakerDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(
            app, WorkoutMakerDatabase::class.java,
            Constant.WORKOUT_MAKER_DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideExerciseMuscleCrossRefDao(database: WorkoutMakerDatabase) =
        database.getExerciseMuscleCrossRefDao()

    @Singleton
    @Provides
    fun provideExerciseNoteCrossRefDao(database: WorkoutMakerDatabase) =
        database.getExerciseNoteCrossRefDao()

    @Singleton
    @Provides
    fun provideEquipmentDao(database: WorkoutMakerDatabase) = database.getEquipmentDao()

    @Singleton
    @Provides
    fun provideExerciseDao(database: WorkoutMakerDatabase) = database.getExerciseDao()

    @Singleton
    @Provides
    fun provideExerciseTypeDao(database: WorkoutMakerDatabase) = database.getExerciseTypeDao()

    @Singleton
    @Provides
    fun provideMacrocycleDao(database: WorkoutMakerDatabase) = database.getMacrocycleDao()

    @Singleton
    @Provides
    fun provideMeasurementDao(database: WorkoutMakerDatabase) = database.getMeasurementDao()

    @Singleton
    @Provides
    fun provideMesocycleDao(database: WorkoutMakerDatabase) = database.getMesocycleDao()

    @Singleton
    @Provides
    fun provideMesocycleTypeDao(database: WorkoutMakerDatabase) = database.getMesocycleTypeDao()

    @Singleton
    @Provides
    fun provideMicrocycleDao(database: WorkoutMakerDatabase) = database.getMicrocycleDao()

    @Singleton
    @Provides
    fun provideMuscleDao(database: WorkoutMakerDatabase) = database.getMuscleDao()

    @Singleton
    @Provides
    fun provideNoteDao(database: WorkoutMakerDatabase) = database.getNoteDao()

    @Singleton
    @Provides
    fun provideSetDao(database: WorkoutMakerDatabase) =
        database.getSetDao()

    @Singleton
    @Provides
    fun provideSetLogDao(database: WorkoutMakerDatabase) =
        database.getSetLogDao()

    @Singleton
    @Provides
    fun provideSetTypeDao(database: WorkoutMakerDatabase) =
        database.getSetTypeDao()

    @Singleton
    @Provides
    fun provideTrainingDao(database: WorkoutMakerDatabase) =
        database.getTrainingDao()

    @Singleton
    @Provides
    fun provideUserDao(database: WorkoutMakerDatabase) =
        database.getUserDao()
}
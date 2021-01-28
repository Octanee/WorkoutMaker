package com.octaneee.workoutmaker.di

import com.octaneee.workoutmaker.other.ApplicationScope
import com.octaneee.workoutmaker.util.WorkoutManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    @ApplicationScope
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun provideWorkoutManager() = WorkoutManager()

}


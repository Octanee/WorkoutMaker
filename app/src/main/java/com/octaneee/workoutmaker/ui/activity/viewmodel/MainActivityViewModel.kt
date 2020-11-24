package com.octaneee.workoutmaker.ui.activity.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.logic.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository =
        UserRepository(WorkoutMakerDatabase.getDatabase(application).getUserDao())

    fun getUser(): User {
        return userRepository.getUser()
    }

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
        }
    }

//    private val exerciseRepository =
//        ExerciseRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseDao())
//    private val muscleRepository =
//        MuscleRepository(WorkoutMakerDatabase.getDatabase(application).getMuscleDao())
//    private val exerciseMuscleCrossRefRepository = ExerciseMuscleCrossRefRepository(
//        WorkoutMakerDatabase.getDatabase(application).getExerciseMuscleCrossRefDao()
//    )
//    private val noteRepository =
//        NoteRepository(WorkoutMakerDatabase.getDatabase(application).getNoteDao())
//    private val exerciseNoteCrossRefRepository =
//        ExerciseNoteCrossRefRepository(
//            WorkoutMakerDatabase.getDatabase(application)
//                .getExerciseNoteCrossRefDao()
//        )
//    private val equipmentRepository =
//        EquipmentRepository(WorkoutMakerDatabase.getDatabase(application).getEquipmentDao())
//    private val exerciseTypeRepository =
//        ExerciseTypeRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseTypeDao())
//
//    fun getWholeExercise(exerciseId: Long): LiveData<WholeExercise> {
//        return exerciseRepository.getWholeExercise(exerciseId)
//    }
//
//
//    fun getExerciseAndExerciseType(exerciseId: Long): LiveData<ExerciseAndExerciseType> {
//        return exerciseTypeRepository.getExerciseAndExerciseType(exerciseId)
//    }
//
//    fun insertExercise(exercise: Exercise) {
//        viewModelScope.launch(Dispatchers.IO) {
//            exerciseRepository.insert(exercise)
//        }
//    }
//
//    fun insertExerciseType(exerciseType: ExerciseType) {
//        viewModelScope.launch(Dispatchers.IO) {
//            exerciseTypeRepository.insert(exerciseType)
//        }
//    }
//
//    fun insertEquipment(equipment: Equipment) {
//        viewModelScope.launch(Dispatchers.IO) {
//            equipmentRepository.insert(equipment)
//        }
//    }
//
//    fun insertExerciseWithNotes(exerciseWithNotes: ExerciseWithNotes) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val exerciseId = exerciseRepository.insert(exerciseWithNotes.exercise)
//
//            for (note in exerciseWithNotes.notes) {
//                val noteId = noteRepository.insert(note)
//                val exerciseNoteCrossRef = ExerciseNoteCrossRef(exerciseId, noteId)
//                exerciseNoteCrossRefRepository.insert(exerciseNoteCrossRef)
//            }
//        }
//    }
//
//    fun deleteExercise(exerciseId: Long) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val exercise = exerciseRepository.getExercise(exerciseId)
//            exerciseRepository.delete(exercise)
//        }
//    }
//
//    fun insertExerciseWithMuscles(exercise: Exercise, muscles: List<Muscle>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val exerciseId = exerciseRepository.insert(exercise)
//
//            for (muscle in muscles) {
//                val muscleId = muscleRepository.insert(muscle)
//                val exerciseMuscleCrossRef = ExerciseMuscleCrossRef(exerciseId, muscleId)
//                exerciseMuscleCrossRefRepository.insert(exerciseMuscleCrossRef)
//            }
//        }
//    }
//
//    fun insertExerciseWithMuscles(exerciseWithMuscles: ExerciseWithMuscles) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val exerciseId = exerciseRepository.insert(exerciseWithMuscles.exercise)
//
//            for (muscle in exerciseWithMuscles.muscles) {
//                val muscleId = muscleRepository.insert(muscle)
//                val exerciseMuscleCrossRef = ExerciseMuscleCrossRef(exerciseId, muscleId)
//                exerciseMuscleCrossRefRepository.insert(exerciseMuscleCrossRef)
//            }
//        }
//    }
//
//    fun getExerciseWithMuscles(): LiveData<List<ExerciseWithMuscles>> {
//        return exerciseMuscleCrossRefRepository.getExerciseWithMuscles()
//    }
//
//    fun getMuscleWithExercises(): LiveData<List<MuscleWithExercises>> {
//        return exerciseMuscleCrossRefRepository.getMuscleWithExercises()
//    }
//
//    fun getExerciseWithMuscles(exerciseId: Long): LiveData<ExerciseWithMuscles> {
//        return exerciseMuscleCrossRefRepository.getExerciseWithMuscles(exerciseId)
//    }
//
//    fun getMuscleWithExercises(muscleId: Long): LiveData<MuscleWithExercises> {
//        return exerciseMuscleCrossRefRepository.getMuscleWithExercises(muscleId)
//    }
}
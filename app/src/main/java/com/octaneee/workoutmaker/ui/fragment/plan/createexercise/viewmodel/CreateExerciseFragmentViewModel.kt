package com.octaneee.workoutmaker.ui.fragment.plan.createexercise.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.entity.Note

class CreateExerciseFragmentViewModel(application: Application) : AndroidViewModel(application) {

    var exerciseName: String? = null
    var primaryMuscle: Muscle? = null
    var additionalMuscles: List<Muscle> = listOf()
    var exerciseType: ExerciseType? = null
    var equipment: Equipment? = null
    var noteList = MutableLiveData<MutableList<Note>>()

    init {
        noteList.value = ArrayList()
    }

//    private val exerciseRepository =
//        ExerciseRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseDao())
//    private val muscleRepository =
//        MuscleRepository(WorkoutMakerDatabase.getDatabase(application).getMuscleDao())
//    private val exerciseTypeRepository =
//        ExerciseTypeRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseTypeDao())
//    private val equipmentRepository =
//        EquipmentRepository(WorkoutMakerDatabase.getDatabase(application).getEquipmentDao())
//    private val exerciseMuscleCrossRefRepository = ExerciseMuscleCrossRefRepository(
//        WorkoutMakerDatabase.getDatabase(application).getExerciseMuscleCrossRefDao()
//    )
//    private val noteRepository =
//        NoteRepository(WorkoutMakerDatabase.getDatabase(application).getNoteDao())
//    private val exerciseNoteCrossRefRepository = ExerciseNoteCrossRefRepository(
//        WorkoutMakerDatabase.getDatabase(application).getExerciseNoteCrossRefDao()
//    )
//
//    val muscleList = muscleRepository.getMuscleList()
//    val exerciseTypeList = exerciseTypeRepository.getExerciseTypeList()
//    val equipmentList = equipmentRepository.getEquipmentList()
//
//
//
//    fun saveExercise(notes: List<Note>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val exercise = Exercise(
//                exerciseName!!,
//                exerciseType!!.exerciseTypeId,
//                equipment!!.equipmentId,
//                primaryMuscle!!.muscleId
//            )
//            val exerciseId = exerciseRepository.insert(exercise)
//
//            for (muscle in additionalMuscles) {
//                exerciseMuscleCrossRefRepository.insert(
//                    ExerciseMuscleCrossRef(
//                        exerciseId,
//                        muscle.muscleId
//                    )
//                )
//            }
//
//            for (note in notes) {
//                val noteId = noteRepository.insert(note)
//                exerciseNoteCrossRefRepository.insert(ExerciseNoteCrossRef(exerciseId, noteId))
//            }
//        }
//    }
}
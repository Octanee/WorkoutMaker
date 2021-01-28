package com.octaneee.workoutmaker.ui.exercise

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.model.entity.Equipment
import com.octaneee.workoutmaker.model.entity.ExerciseType
import com.octaneee.workoutmaker.model.entity.Muscle
import com.octaneee.workoutmaker.model.entity.Note
import com.octaneee.workoutmaker.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.model.relations.FullExercise
import com.octaneee.workoutmaker.repository.*
import com.octaneee.workoutmaker.repository.crossref.ExerciseNoteCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExerciseViewModel @ViewModelInject constructor(
    private val exerciseRepository: ExerciseRepository,
    private val noteRepository: NoteRepository,
    private val exerciseNoteCrossRefRepository: ExerciseNoteCrossRefRepository,
    private val muscleRepository: MuscleRepository,
    private val exerciseTypeRepository: ExerciseTypeRepository,
    private val equipmentRepository: EquipmentRepository
) : ViewModel() {

    var muscleDataSet: LiveData<List<Muscle>> = muscleRepository.getMuscleList()
    var exerciseTypeDataSet: LiveData<List<ExerciseType>> =
        exerciseTypeRepository.getExerciseTypeList()
    var equipmentDataSet: LiveData<List<Equipment>> = equipmentRepository.getEquipmentList()

    var muscle: Muscle? = null
    var equipment: Equipment? = null
    var exerciseType: ExerciseType? = null
    var editedNote: Note? = null

    lateinit var fullExercise: LiveData<FullExercise>

    fun setupFullExercise(exerciseId: Long) {
        fullExercise = exerciseRepository.getFullExerciseByExerciseId(exerciseId)
    }

    fun deleteNote(item: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.delete(item)
    }

    fun insertNote(item: Note, exerciseId: Long) = viewModelScope.launch(Dispatchers.IO) {
        val noteId = noteRepository.insert(item)
        val exerciseNoteCrossRef = ExerciseNoteCrossRef(exerciseId, noteId)
        exerciseNoteCrossRefRepository.insert(exerciseNoteCrossRef)
    }

    fun updateNote(item: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(item)
    }

    fun updateExercise() = viewModelScope.launch(Dispatchers.IO) {
        val exercise = fullExercise.value!!.exercise
        equipment?.let {
            exercise.equipmentIdFk = it.equipmentId
        }
        exerciseType?.let {
            exercise.exerciseTypeIdFk = it.exerciseTypeId
        }
        muscle?.let {
            exercise.muscleIdFk = it.muscleId
        }
        exerciseRepository.update(exercise)
    }
}
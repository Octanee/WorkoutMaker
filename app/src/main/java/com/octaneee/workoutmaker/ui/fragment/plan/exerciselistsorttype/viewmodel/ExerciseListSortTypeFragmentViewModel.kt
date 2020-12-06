package com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.Equipment
import com.octaneee.workoutmaker.data.model.entity.ExerciseType
import com.octaneee.workoutmaker.data.model.entity.Muscle
import com.octaneee.workoutmaker.data.model.relation.MacrocycleWithMesocycles
import com.octaneee.workoutmaker.data.model.relation.MesocycleAndMesocycleTypeWithMicrocycles
import com.octaneee.workoutmaker.logic.repository.EquipmentRepository
import com.octaneee.workoutmaker.logic.repository.ExerciseTypeRepository
import com.octaneee.workoutmaker.logic.repository.MuscleRepository
import com.octaneee.workoutmaker.ui.fragment.plan.exerciselistsorttype.ExerciseListSortTypeFragment

class ExerciseListSortTypeFragmentViewModel(application: Application) :
    AndroidViewModel(application) {

    private val muscleRepository =
        MuscleRepository(WorkoutMakerDatabase.getDatabase(application).getMuscleDao())
    private val exerciseTypeRepository =
        ExerciseTypeRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseTypeDao())
    private val equipmentRepository =
        EquipmentRepository(WorkoutMakerDatabase.getDatabase(application).getEquipmentDao())

    val muscleListLiveData = muscleRepository.getMuscleList()
    val equipmentListLiveData = equipmentRepository.getEquipmentList()
    val exerciseTypeListLiveData = exerciseTypeRepository.getExerciseTypeList()

    var muscleList: List<Muscle> = listOf()
    var equipmentList: List<Equipment> = listOf()
    var exerciseTypeList: List<ExerciseType> = listOf()

    var sortType = ExerciseListSortTypeFragment.SortType.Muscle

    lateinit var macrocycleWithMesocycles: MacrocycleWithMesocycles
    lateinit var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles
}
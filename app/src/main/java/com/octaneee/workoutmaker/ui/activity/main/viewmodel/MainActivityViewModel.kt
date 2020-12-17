package com.octaneee.workoutmaker.ui.activity.main.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.repository.*
import com.octaneee.workoutmaker.repository.crossref.ExerciseMuscleCrossRefRepository
import com.octaneee.workoutmaker.repository.crossref.ExerciseNoteCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

class MainActivityViewModel @ViewModelInject constructor(
    val userRepository: UserRepository,
    val exerciseMuscleCrossRefRepository: ExerciseMuscleCrossRefRepository,
    val exerciseNoteCrossRefRepository: ExerciseNoteCrossRefRepository,
    val equipmentRepository: EquipmentRepository,
    val exerciseRepository: ExerciseRepository,
    val exerciseTypeRepository: ExerciseTypeRepository,
    val macrocycleRepository: MacrocycleRepository,
    val measurementRepository: MeasurementRepository,
    val mesocycleRepository: MesocycleRepository,
    val mesocycleTypeRepository: MesocycleTypeRepository,
    val microcycleRepository: MicrocycleRepository,
    val muscleRepository: MuscleRepository,
    val noteRepository: NoteRepository,
    val setRepository: SetRepository,
    val setLogRepository: SetLogRepository,
    val setTypeRepository: SetTypeRepository,
    val trainingRepository: TrainingRepository,
) : ViewModel() {


    val user = userRepository.getUser(1)


    // Populate database

    fun populateDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.i("Start")
            val exerciseMuscleCrossRefs = mutableListOf<ExerciseMuscleCrossRef>()
            val exerciseNoteCrossRefs = mutableListOf<ExerciseNoteCrossRef>()
            val equipments = mutableListOf<Equipment>()
            val exercises = mutableListOf<Exercise>()
            val exerciseTypes = mutableListOf<ExerciseType>()
            val macrocycles = mutableListOf<Macrocycle>()
            val measurements = mutableListOf<Measurement>()
            val mesocycles = mutableListOf<Mesocycle>()
            val mesocycleTypes = mutableListOf<MesocycleType>()
            val microcycles = mutableListOf<Microcycle>()
            val muscles = mutableListOf<Muscle>()
            val notes = mutableListOf<Note>()
            val sets = mutableListOf<Set>()
            val setLogs = mutableListOf<SetLog>()
            val setTypes = mutableListOf<SetType>()
            val trainings = mutableListOf<Training>()
            val users = mutableListOf<User>()

            for (i in 1..10) {
                equipments.add(
                    Equipment(
                        "Equipment $i",
                        context.resources.getResourceEntryName(R.drawable.ic_gym)
                    )
                )
            }
            val equipmentId = equipmentRepository.insert(equipments)
            Timber.i("Added Equipment")

            for (i in 1..5) {
                exerciseTypes.add(
                    ExerciseType(
                        "Exercise Type $i",
                        context.resources.getResourceEntryName(R.drawable.ic_body)
                    )
                )
            }
            val exerciseTypeId = exerciseTypeRepository.insert(exerciseTypes)
            Timber.i("Added ExerciseType")

            for (i in 1..5) {
                macrocycles.add(Macrocycle("Macrocycle $i"))
            }
            val macrocycleId = macrocycleRepository.insert(macrocycles)
            Timber.i("Added Macrocycle")

            for (i in 1..5) {
                mesocycleTypes.add(MesocycleType("MesocycleType $i"))
            }
            val mesocycleTypeId = mesocycleTypeRepository.insert(mesocycleTypes)
            Timber.i("Added MeasocycleType")

            for (i in 1..20) {
                muscles.add(
                    Muscle(
                        "Muscle $i",
                        context.resources.getResourceEntryName(R.drawable.man_figure_front_chest)
                    )
                )
            }
            val muscleId = muscleRepository.insert(muscles)
            Timber.i("Added Muscle")

            for (i in 1..100) {
                notes.add(Note("Note $i"))
            }
            val noteId = noteRepository.insert(notes)
            Timber.i("Added Note")

            for (i in 1..5) {
                setTypes.add(SetType("SetType $i"))
            }
            val setTypeId = setTypeRepository.insert(setTypes)
            Timber.i("Added SetType")

            for (i in 1..30) {
                exercises.add(
                    Exercise(
                        "Exercise $i",
                        exerciseTypeId.random(),
                        equipmentId.random(),
                        muscleId.random()
                    )
                )
            }
            val exerciseId = exerciseRepository.insert(exercises)
            Timber.i("Added Exercise")

            for (i in 1..20) {
                mesocycles.add(
                    Mesocycle(
                        "Mesocycle $i",
                        mesocycleTypeId.random(),
                        macrocycleId.random()
                    )
                )
            }
            val mesocycleId = mesocycleRepository.insert(mesocycles)
            Timber.i("Added Mesocycle")

            for (i in 1..50) {
                microcycles.add(Microcycle("Microcycle $i", mesocycleId.random(), (5..10).random()))
            }
            val microcycleId = microcycleRepository.insert(microcycles)
            Timber.i("Added Microcycle")

            for (i in 1..50) {
                trainings.add(Training("Training $i", microcycleId.random(), (1..7).random()))
            }
            val trainingId = trainingRepository.insert(trainings)
            Timber.i("Added Training")

            for (i in 1..5) {
                users.add(User("User $i", 180, macrocycleId.random()))
            }
            val userId = userRepository.insert(users)
            Timber.i("Added User")

            for (i in 1..20) {
                measurements.add(Measurement(userId.random(), Calendar.getInstance().time))
            }
            val measurementId = measurementRepository.insert(measurements)
            Timber.i("Added Measurement")

            for (i in 1..100) {
                exerciseMuscleCrossRefs.add(
                    ExerciseMuscleCrossRef(
                        exerciseId.random(),
                        muscleId.random()
                    )
                )
            }
            val exerciseMuscleCrossRefId =
                exerciseMuscleCrossRefRepository.insert(exerciseMuscleCrossRefs)
            Timber.i("Added ExerciseMuscleCrossRef")

            for (i in 1..100) {
                exerciseNoteCrossRefs.add(
                    ExerciseNoteCrossRef(
                        exerciseId.random(),
                        noteId.random()
                    )
                )
            }
            val exerciseNoteCrossRefId =
                exerciseNoteCrossRefRepository.insert(exerciseNoteCrossRefs)
            Timber.i("Added ExerciseNoteCrossRef")

            for (i in 1..100) {
                sets.add(
                    Set(
                        trainingId.random(),
                        exerciseId.random(),
                        setTypeId.random(),
                        (4..8).random()
                    )
                )
            }
            val setId = setRepository.insert(sets)
            Timber.i("Added Set")

            for (i in 1..50) {
                setLogs.add(
                    SetLog(
                        setId.random(),
                        (2..6).random(),
                        (0..3).random(),
                        Calendar.getInstance().time
                    )
                )
            }
            val setLogId = setLogRepository.insert(setLogs)
            Timber.i("Added SetLog")

            Timber.i("END")
        }
    }
}
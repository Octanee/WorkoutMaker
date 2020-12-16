package com.octaneee.workoutmaker.ui.activity.main.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.data.database.WorkoutMakerDatabase
import com.octaneee.workoutmaker.data.model.entity.*
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseMuscleCrossRef
import com.octaneee.workoutmaker.data.model.entity.crossref.ExerciseNoteCrossRef
import com.octaneee.workoutmaker.data.model.relation.*
import com.octaneee.workoutmaker.logic.repository.*
import com.octaneee.workoutmaker.logic.repository.crossref.ExerciseMuscleCrossRefRepository
import com.octaneee.workoutmaker.logic.repository.crossref.ExerciseNoteCrossRefRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository =
        UserRepository(WorkoutMakerDatabase.getDatabase(application).getUserDao())

    var macrocycleWithMesocycles: MacrocycleWithMesocycles? = null
    var mesocycleAndMesocycleTypeWithMicrocycles: MesocycleAndMesocycleTypeWithMicrocycles? = null
    var microcycleWithTrainings: MicrocycleWithTrainings? = null
    var trainingWithSetAndExercise: TrainingWithSetAndExercises? = null
    var setAndExercisesList: MutableList<SetAndExercise> = mutableListOf()

    val userAndMacrocycle = userRepository.getUser(1)

    fun setCurrentMacrocycle(macrocycleId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            userAndMacrocycle.value?.let {
                val newUser = it.user
                newUser.macrocycleId = macrocycleId
                userRepository.update(newUser)
            }
        }
    }

    private val exerciseMuscleCrossRefRepository = ExerciseMuscleCrossRefRepository(
        WorkoutMakerDatabase.getDatabase(application).getExerciseMuscleCrossRefDao()
    )
    private val exerciseNoteCrossRefRepository = ExerciseNoteCrossRefRepository(
        WorkoutMakerDatabase.getDatabase(application).getExerciseNoteCrossRefDao()
    )
    private val equipmentRepository =
        EquipmentRepository(WorkoutMakerDatabase.getDatabase(application).getEquipmentDao())
    private val exerciseRepository =
        ExerciseRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseDao())
    private val exerciseTypeRepository =
        ExerciseTypeRepository(WorkoutMakerDatabase.getDatabase(application).getExerciseTypeDao())
    private val macrocycleRepository =
        MacrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMacrocycleDao())
    private val measurementRepository =
        MeasurementRepository(WorkoutMakerDatabase.getDatabase(application).getMeasurementDao())
    private val mesocycleRepository =
        MesocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleDao())
    private val mesocycleTypeRepository =
        MesocycleTypeRepository(WorkoutMakerDatabase.getDatabase(application).getMesocycleTypeDao())
    private val microcycleRepository =
        MicrocycleRepository(WorkoutMakerDatabase.getDatabase(application).getMicrocycleDao())
    private val muscleRepository =
        MuscleRepository(WorkoutMakerDatabase.getDatabase(application).getMuscleDao())
    private val noteRepository =
        NoteRepository(WorkoutMakerDatabase.getDatabase(application).getNoteDao())
    private val setRepository =
        SetRepository(WorkoutMakerDatabase.getDatabase(application).getSetDao())
    private val setLogRepository =
        SetLogRepository(WorkoutMakerDatabase.getDatabase(application).getSetLogDao())
    private val setTypeRepository =
        SetTypeRepository(WorkoutMakerDatabase.getDatabase(application).getSetTypeDao())
    private val trainingRepository =
        TrainingRepository(WorkoutMakerDatabase.getDatabase(application).getTrainingDao())


    fun populateDatabase(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("PopulateDatabase", "Start")
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
            Log.d("PopulateDatabase", "Added Equipment")

            for (i in 1..5) {
                exerciseTypes.add(
                    ExerciseType(
                        "Exercise Type $i",
                        context.resources.getResourceEntryName(R.drawable.ic_body)
                    )
                )
            }
            val exerciseTypeId = exerciseTypeRepository.insert(exerciseTypes)
            Log.d("PopulateDatabase", "Added ExerciseType")

            for (i in 1..5) {
                macrocycles.add(Macrocycle("Macrocycle $i"))
            }
            val macrocycleId = macrocycleRepository.insert(macrocycles)
            Log.d("PopulateDatabase", "Added Macrocycle")

            for (i in 1..5) {
                mesocycleTypes.add(MesocycleType("MesocycleType $i"))
            }
            val mesocycleTypeId = mesocycleTypeRepository.insert(mesocycleTypes)
            Log.d("PopulateDatabase", "Added MeasocycleType")

            for (i in 1..20) {
                muscles.add(
                    Muscle(
                        "Muscle $i",
                        context.resources.getResourceEntryName(R.drawable.man_figure_front_chest)
                    )
                )
            }
            val muscleId = muscleRepository.insert(muscles)
            Log.d("PopulateDatabase", "Added Muscle")

            for (i in 1..100) {
                notes.add(Note("Note $i"))
            }
            val noteId = noteRepository.insert(notes)
            Log.d("PopulateDatabase", "Added Note")

            for (i in 1..5) {
                setTypes.add(SetType("SetType $i"))
            }
            val setTypeId = setTypeRepository.insert(setTypes)
            Log.d("PopulateDatabase", "Added SetType")

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
            Log.d("PopulateDatabase", "Added Exercise")

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
            Log.d("PopulateDatabase", "Added Mesocycle")

            for (i in 1..50) {
                microcycles.add(Microcycle("Microcycle $i", mesocycleId.random(), (5..10).random()))
            }
            val microcycleId = microcycleRepository.insert(microcycles)
            Log.d("PopulateDatabase", "Added Microcycle")

            for (i in 1..50) {
                trainings.add(Training("Training $i", microcycleId.random(), (1..7).random()))
            }
            val trainingId = trainingRepository.insert(trainings)
            Log.d("PopulateDatabase", "Added Training")

            for (i in 1..5) {
                users.add(User("User $i", 180, macrocycleId.random()))
            }
            val userId = userRepository.insert(users)
            Log.d("PopulateDatabase", "Added User")

            for (i in 1..20) {
                measurements.add(Measurement(userId.random(), Calendar.getInstance().time))
            }
            val measurementId = measurementRepository.insert(measurements)
            Log.d("PopulateDatabase", "Added Measurement")

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
            Log.d("PopulateDatabase", "Added ExerciseMuscleCrossRef")

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
            Log.d("PopulateDatabase", "Added ExerciseNoteCrossRef")

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
            Log.d("PopulateDatabase", "Added Set")

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
            Log.d("PopulateDatabase", "Added SetLog")

            Log.d("PopulateDatabase", "END")
        }
    }

    val trainingWithSetAndExercisesList = trainingRepository.getTrainingWithSetAndExercises()
    val macrocycles = macrocycleRepository.getMacrocycles()
}
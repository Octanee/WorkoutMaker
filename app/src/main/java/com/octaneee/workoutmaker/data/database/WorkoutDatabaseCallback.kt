package com.octaneee.workoutmaker.data.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.octaneee.workoutmaker.R
import com.octaneee.workoutmaker.model.entity.*
import com.octaneee.workoutmaker.other.ApplicationScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class WorkoutDatabaseCallback @Inject constructor(
    private val database: Provider<WorkoutMakerDatabase>,
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)

        Timber.d("Callback onCreate")
        val equipmentRepository = database.get().getEquipmentDao()
        val exerciseRepository = database.get().getExerciseDao()
        val exerciseTypeRepository = database.get().getExerciseTypeDao()
        val mesocycleTypeRepository = database.get().getMesocycleTypeDao()
        val muscleRepository = database.get().getMuscleDao()
        val setTypeRepository = database.get().getSetTypeDao()

        applicationScope.launch {
            Timber.d("Callback launch")
            val equipments = mutableListOf<Equipment>()
            val exercises = mutableListOf<Exercise>()
            val exerciseTypes = mutableListOf<ExerciseType>()
            val mesocycleTypes = mutableListOf<MesocycleType>()
            val muscles = mutableListOf<Muscle>()
            val setTypes = mutableListOf<SeriesType>()

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
                mesocycleTypes.add(MesocycleType("MesocycleType $i"))
            }
            mesocycleTypeRepository.insert(mesocycleTypes)
            Timber.i("Added MesocycleType")

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

            for (i in 1..5) {
                setTypes.add(SeriesType("SeriesType $i"))
            }
            setTypeRepository.insert(setTypes)
            Timber.i("Added SeriesType")

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
            exerciseRepository.insert(exercises)
            Timber.i("Added Exercise")

            Timber.i("END")
        }
    }

    private suspend fun insertEquipment() {
        //val equipmentRepository = database.get().getEquipmentDao()
        //val list = [Equipment("")]
//        list.forEach {
//            equipmentRepository.insert(it)
//        }
    }
}
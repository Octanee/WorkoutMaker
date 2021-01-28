package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.relations.FullExercise
import com.octaneee.workoutmaker.other.ExerciseOrderType

@Dao
interface ExerciseDao : BaseDao<Exercise> {

    fun getExerciseList(
        orderType: ExerciseOrderType,
        muscleId: Long?,
        exerciseTypeId: Long?,
        equipmentId: Long?
    ): LiveData<List<Exercise>> {
        return when (orderType) {
            ExerciseOrderType.ORDER_BY_MUSCLE -> getExerciseSortedListByMuscle(
                muscleId,
                exerciseTypeId,
                equipmentId
            )
            ExerciseOrderType.ORDER_BY_EXERCISE_TYPE -> getExerciseSortedListByEquipment(
                muscleId,
                exerciseTypeId,
                equipmentId
            )
            ExerciseOrderType.ORDER_BY_EQUIPMENT -> getExerciseSortedListByExerciseType(
                muscleId,
                exerciseTypeId,
                equipmentId
            )
        }
    }

    @Query("SELECT * FROM exercise_table WHERE (:muscleId IS NULL OR muscleIdFk = :muscleId) AND (:exerciseTypeId IS NULL OR exerciseTypeIdFk = :exerciseTypeId) AND (:equipmentId IS NULL OR equipmentIdFk = :equipmentId) ORDER BY muscleIdFk, exerciseName")
    fun getExerciseSortedListByMuscle(
        muscleId: Long?,
        exerciseTypeId: Long?,
        equipmentId: Long?,
    ): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_table WHERE (:muscleId IS NULL OR muscleIdFk = :muscleId) AND (:exerciseTypeId IS NULL OR exerciseTypeIdFk = :exerciseTypeId) AND (:equipmentId IS NULL OR equipmentIdFk = :equipmentId) ORDER BY equipmentIdFk, exerciseName")
    fun getExerciseSortedListByEquipment(
        muscleId: Long?,
        exerciseTypeId: Long?,
        equipmentId: Long?,
    ): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_table WHERE  (:muscleId IS NULL OR muscleIdFk = :muscleId) AND (:exerciseTypeId IS NULL OR exerciseTypeIdFk = :exerciseTypeId) AND (:equipmentId IS NULL OR equipmentIdFk = :equipmentId) ORDER BY exerciseTypeIdFk, exerciseName")
    fun getExerciseSortedListByExerciseType(
        muscleId: Long?,
        exerciseTypeId: Long?,
        equipmentId: Long?,
    ): LiveData<List<Exercise>>

    @Transaction
    @Query("SELECT * FROM exercise_table WHERE exerciseId = :exerciseId")
    fun getFullExerciseByExerciseId(exerciseId: Long): LiveData<FullExercise>
}
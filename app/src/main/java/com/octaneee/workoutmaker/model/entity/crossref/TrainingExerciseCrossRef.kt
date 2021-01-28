package com.octaneee.workoutmaker.model.entity.crossref

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Training
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "training_exercise_cross_ref",
    foreignKeys = [
        ForeignKey(
            entity = Training::class,
            parentColumns = ["trainingId"],
            childColumns = ["trainingIdFk"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseIdFk"],
            onDelete = ForeignKey.CASCADE
        )
    ],

    )
data class TrainingExerciseCrossRef(
    val trainingIdFk: Long,
    val exerciseIdFk: Long
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var trainingExerciseCrossRefId: Long = 0
}

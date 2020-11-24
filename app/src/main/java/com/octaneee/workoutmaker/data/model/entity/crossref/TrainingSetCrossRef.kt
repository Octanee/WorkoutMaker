package com.octaneee.workoutmaker.data.model.entity.crossref

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.Set
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "training_set_cross_ref",
    foreignKeys = [
        ForeignKey(
            entity = Training::class,
            parentColumns = ["trainingId"],
            childColumns = ["trainingId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Set::class,
            parentColumns = ["setId"],
            childColumns = ["setId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TrainingSetCrossRef(
    val trainingId: Long,
    val setId: Long
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var trainingSetId: Long = 0
}
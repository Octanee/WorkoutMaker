package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef

@Entity(
    tableName = "series_table",
    foreignKeys = [
        ForeignKey(
            entity = TrainingExerciseCrossRef::class,
            parentColumns = ["trainingExerciseCrossRefId"],
            childColumns = ["trainingExerciseCrossRefIdFk"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SeriesType::class,
            parentColumns = ["seriesTypeId"],
            childColumns = ["seriesTypeIdFk"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class Series(
    var seriesTypeIdFk: Long? = null,
    var trainingExerciseCrossRefIdFk: Long? = null,
    var minimum: Int? = null,
    var maximum: Int? = null,
    var weight: Float? = null,
    var restTime: Int? = null,
    var repsInReserve: Int? = null,
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var seriesId: Long = 0
}
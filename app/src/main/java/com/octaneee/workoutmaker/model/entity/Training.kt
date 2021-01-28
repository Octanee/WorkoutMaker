package com.octaneee.workoutmaker.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

@Entity(
    tableName = "training_table",
    foreignKeys = [
        ForeignKey(
            entity = Microcycle::class,
            parentColumns = ["microcycleId"],
            childColumns = ["microcycleIdFk"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class Training(
    var trainingName: String,
    var microcycleIdFk: Long,
    var dayOfMicrocycle: Int
) : BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var trainingId: Long = 0

    override fun toString(): String = trainingName
}
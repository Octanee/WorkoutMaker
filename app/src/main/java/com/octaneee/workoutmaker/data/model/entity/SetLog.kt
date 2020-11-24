package com.octaneee.workoutmaker.data.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import com.octaneee.workoutmaker.data.model.entity.crossref.TrainingSetCrossRef
import java.util.*

@Entity(
    tableName = "set_log_table",
    foreignKeys = [
        ForeignKey(
            entity = TrainingSetCrossRef::class,
            parentColumns = ["trainingSetId"],
            childColumns = ["trainingSetId"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
data class SetLog(
    val trainingSetId: Long,
    val quantity: Int,
    val repsInReserve: Int,
    val date: Date
) : BaseEntity {
    @PrimaryKey(autoGenerate = true)
    var setLogId: Long = 0
}
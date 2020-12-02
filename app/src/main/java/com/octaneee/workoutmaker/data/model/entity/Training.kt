package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "training_table",
    foreignKeys = [
        ForeignKey(
            entity = Microcycle::class,
            parentColumns = ["microcycleId"],
            childColumns = ["microcycleId"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
@Parcelize
data class Training(
    val name: String,
    val microcycleId: Long,
    val dayOfMicrocycle: Int
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var trainingId: Long = 0
}
package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "microcycle_table",
    foreignKeys = [
        ForeignKey(
            entity = Mesocycle::class,
            parentColumns = ["mesocycleId"],
            childColumns = ["mesocycleId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
@Parcelize
data class Microcycle(
    var microcycleName: String,
    var mesocycleId: Long?,
    var numberOfDays: Int
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var microcycleId: Long = 0

    override fun toString(): String = "($mesocycleId) $microcycleName"
}
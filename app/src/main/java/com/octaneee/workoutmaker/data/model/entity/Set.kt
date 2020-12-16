package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "set_table",
    foreignKeys = [
        ForeignKey(
            entity = Training::class,
            parentColumns = ["trainingId"],
            childColumns = ["trainingId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SetType::class,
            parentColumns = ["setTypeId"],
            childColumns = ["setTypeId"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
@Parcelize
data class Set(
    val trainingId: Long,
    val exerciseId: Long,
    val setTypeId: Long,
    val minimum: Int,
    val maximum: Int? = null,
    val restTime: Int? = null,
    val repsInReserve: Int? = null
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var setId: Long = 0

    override fun toString(): String {
        return "S$setId - T$trainingId - E$exerciseId"
    }
}
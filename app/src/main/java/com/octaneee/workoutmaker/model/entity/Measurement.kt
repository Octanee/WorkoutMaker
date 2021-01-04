package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "measurement_table",
)
data class Measurement(
    val measurementDate: Date
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var measurementId: Long = 0
}

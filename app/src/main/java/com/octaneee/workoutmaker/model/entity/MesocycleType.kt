package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "mesocycle_type_table")
@Parcelize
data class MesocycleType(
    val mesocycleTypeName: String
) : BaseEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    var mesocycleTypeId: Long = 0
    override fun toString(): String = mesocycleTypeName
}
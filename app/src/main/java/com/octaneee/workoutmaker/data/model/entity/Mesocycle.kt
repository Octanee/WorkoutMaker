package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "mesocycle_table",
    foreignKeys = [
        ForeignKey(
            entity = MesocycleType::class,
            parentColumns = ["mesocycleTypeId"],
            childColumns = ["mesocycleTypeId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Macrocycle::class,
            parentColumns = ["macrocycleId"],
            childColumns = ["macrocycleId"],
            onDelete = ForeignKey.SET_NULL

        )]
)
@Parcelize
data class Mesocycle(
    var name: String,
    val mesocycleTypeId: Long,
    var macrocycleId: Long
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var mesocycleId: Long = 0
}
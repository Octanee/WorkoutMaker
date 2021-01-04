package com.octaneee.workoutmaker.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import com.octaneee.workoutmaker.model.entity.crossref.TrainingExerciseCrossRef
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "set_table",
    foreignKeys = [
        ForeignKey(
            entity = TrainingExerciseCrossRef::class,
            parentColumns = ["trainingExerciseCrossRefId"],
            childColumns = ["trainingExerciseCrossRefIdFk"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = SetType::class,
            parentColumns = ["setTypeId"],
            childColumns = ["setTypeIdFk"],
            onDelete = ForeignKey.SET_NULL,
        )
    ]
)
@Parcelize
data class Set(
    var setTypeIdFk: Long? = null,
    var trainingExerciseCrossRefIdFk: Long? = null,
    var minimum: Int? = null,
    var maximum: Int? = null,
    var restTime: Int? = null,
    var repsInReserve: Int? = null,
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var setId: Long = 0
}
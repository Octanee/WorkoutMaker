package com.octaneee.workoutmaker.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "user_table",
    foreignKeys = [
        ForeignKey(
            entity = Macrocycle::class,
            parentColumns = ["macrocycleId"],
            childColumns = ["macrocycleId"],
            onDelete = ForeignKey.SET_NULL
        )]
)
@Parcelize
data class User(
    val userName: String,
    val height: Int,
    var macrocycleId: Long? = null
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0

    override fun toString(): String = "($userId) $userName"
}

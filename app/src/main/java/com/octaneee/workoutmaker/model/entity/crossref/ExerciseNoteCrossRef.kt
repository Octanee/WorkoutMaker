package com.octaneee.workoutmaker.model.entity.crossref

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.octaneee.workoutmaker.model.entity.Exercise
import com.octaneee.workoutmaker.model.entity.Note
import com.octaneee.workoutmaker.model.entity.base.BaseEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "exercise_note_cross_ref",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseIdFk"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Note::class,
            parentColumns = ["noteId"],
            childColumns = ["noteIdFk"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseNoteCrossRef(
    val exerciseIdFk: Long,
    val noteIdFk: Long
) : BaseEntity, Parcelable {
    @PrimaryKey(autoGenerate = true)
    var exerciseNoteCrossRefId: Long = 0
}
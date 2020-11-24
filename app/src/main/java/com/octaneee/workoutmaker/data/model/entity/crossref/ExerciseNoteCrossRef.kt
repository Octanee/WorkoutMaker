package com.octaneee.workoutmaker.data.model.entity.crossref

import androidx.room.Entity
import androidx.room.ForeignKey
import com.octaneee.workoutmaker.data.model.entity.Exercise
import com.octaneee.workoutmaker.data.model.entity.Note
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Entity(
    tableName = "exercise_note_cross_ref",
    primaryKeys = ["exerciseId", "noteId"],
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["exerciseId"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Note::class,
            parentColumns = ["noteId"],
            childColumns = ["noteId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExerciseNoteCrossRef(
    val exerciseId: Long,
    val noteId: Long
) : BaseEntity
package com.octaneee.workoutmaker.data.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.octaneee.workoutmaker.data.model.entity.Microcycle
import com.octaneee.workoutmaker.data.model.entity.Training
import com.octaneee.workoutmaker.data.model.entity.crossref.TrainingSetCrossRef

data class MicrocycleWithTrainingSetCrossRef(
    @Embedded
    val microcycle: Microcycle,
    @Relation(
        parentColumn = "microcycleId",
        entity = Training::class,
        entityColumn = "microcycleId"
    )
    val trainingSets: List<TrainingSetCrossRef>
)

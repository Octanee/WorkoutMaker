package com.octaneee.workoutmaker.data.dao.base

import androidx.room.*
import com.octaneee.workoutmaker.data.model.entity.base.BaseEntity

@Dao
interface BaseDao<T : BaseEntity> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entities: List<T>): List<Long>

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun delete(entities: List<T>)
}
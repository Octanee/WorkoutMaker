package com.octaneee.workoutmaker.repository.base

import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.model.entity.base.BaseEntity

abstract class BaseRepository<T : BaseEntity>(private val dao: BaseDao<T>) {

    suspend fun insert(entity: T): Long = dao.insert(entity)

    suspend fun insert(entities: List<T>): List<Long> = dao.insert(entities)

    suspend fun insertOrUpdate(entity: T): Long = dao.insertOrUpdate(entity)

    suspend fun insertOrUpdate(entities: List<T>): List<Long> = dao.insertOrUpdate(entities)

    suspend fun update(entity: T) = dao.update(entity)

    suspend fun update(entities: List<T>) = dao.update(entities)

    suspend fun delete(entity: T) = dao.delete(entity)

    suspend fun delete(entities: List<T>) = dao.delete(entities)
}
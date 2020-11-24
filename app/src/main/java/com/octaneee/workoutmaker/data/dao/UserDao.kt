package com.octaneee.workoutmaker.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.User

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM user_table ORDER BY userId DESC LIMIT 1")
    fun getUser(): User
}
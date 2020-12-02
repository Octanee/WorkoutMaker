package com.octaneee.workoutmaker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.octaneee.workoutmaker.data.dao.base.BaseDao
import com.octaneee.workoutmaker.data.model.entity.User
import com.octaneee.workoutmaker.data.model.relation.UserAndMacrocycle

@Dao
interface UserDao : BaseDao<User> {

    @Query("SELECT * FROM user_table WHERE userId = :userId")
    fun getUser(userId: Long): LiveData<UserAndMacrocycle>
}
package com.example.myapplication2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication2.dto.UserWithSubscriptionLevel
import com.example.myapplication2.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("""
        SELECT u.id AS userId,
        u.nickname AS userNickname,
        u.daysOfSubscription,
        s.id AS subscriptionLevelId,
        s.name AS subscriptionLevelName
        FROM users u
        JOIN subscriptionLevels s ON u.subscriptionLevelId = s.id
    """)
    fun getUsersWithSubscriptionLevel(): Flow<List<UserWithSubscriptionLevel>> ///

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}
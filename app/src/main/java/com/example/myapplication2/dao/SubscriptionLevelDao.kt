package com.example.myapplication2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication2.entity.SubscriptionLevel
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriptionLevelDao {

    @Query("SELECT * FROM subscriptionLevels")
    fun getAllSubscriptionLevels(): Flow<List<SubscriptionLevel>>

    @Insert
    suspend fun insert(subscriptionLevel: SubscriptionLevel)

    @Update
    suspend fun update(subscriptionLevel: SubscriptionLevel)

    @Delete
    suspend fun delete(subscriptionLevel: SubscriptionLevel)
}
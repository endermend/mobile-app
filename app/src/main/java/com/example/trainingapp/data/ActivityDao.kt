package com.example.trainingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addActivity(activity: Activity)

    @Query("DELETE FROM user_activity WHERE id = :id")
    suspend fun deleteActivityById(id: Int)

    @Query("SELECT * FROM user_activity")
    fun readActivities(): LiveData<List<Activity>>
}
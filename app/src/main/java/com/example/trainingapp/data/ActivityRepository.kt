package com.example.trainingapp.data

import androidx.lifecycle.LiveData

class ActivityRepository(private val activityDao: ActivityDao) {
    val readAllActivities : LiveData<List<Activity>> = activityDao.readActivities()

    suspend fun addActivity(activity: Activity){
        activityDao.addActivity(activity)
    }

    suspend fun deleteActivityById(id: Int){
        activityDao.deleteActivityById(id)
    }
}
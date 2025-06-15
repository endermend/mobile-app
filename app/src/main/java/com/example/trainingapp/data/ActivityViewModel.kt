package com.example.trainingapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    val readAllActivities: LiveData<List<Activity>>
    private val repository: ActivityRepository

    init {
        val activityDao = ActivityDatabase.getDatabase(application).activityDao()
        repository = ActivityRepository(activityDao)
        readAllActivities = repository.readAllActivities
    }

    fun addActivity(activity: Activity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addActivity(activity)
        }
    }

    fun deleteActivityById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteActivityById(id)
        }
    }
}
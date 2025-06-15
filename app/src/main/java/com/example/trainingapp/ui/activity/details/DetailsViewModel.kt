package com.example.trainingapp.ui.activity.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.ui.activity.models.ActivityUIModel

class DetailsViewModel : ViewModel() {
    val activity = MutableLiveData<ActivityUIModel>()
    val id = MutableLiveData<Int>()
}
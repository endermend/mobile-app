package com.example.trainingapp.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonalViewModel : ViewModel() {

    private val _login = MutableLiveData<String>().apply {
        value = "SuperSons"
    }
    private val _nickname = MutableLiveData<String>().apply {
        value = "Dead By Daylight"
    }
    val login: LiveData<String> = _login
    val nickname: LiveData<String> = _nickname
}
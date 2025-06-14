package com.example.trainingapp.ui.startactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class StartActivityViewModel : ViewModel() {
    val typeList: List<String> = listOf("Велосипед", "Бег", "Шаг")
    val start = MutableLiveData<LocalDateTime>()
    val typeId = MutableLiveData(0)
    val type: LiveData<String> = typeId.map { typeList[it] }
    val time = MutableLiveData(0)
    private var timerJob: Job? = null

    fun startTimer() {
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                time.postValue(time.value?.plus(1) ?: 0)
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

    fun resetTimer() {
        time.value = 0
    }

    val formattedTime: LiveData<String> = time.map { seconds ->
        "%02d:%02d:%02d".format(seconds / 3600, (seconds / 60) % 60, seconds % 60)
    }
}
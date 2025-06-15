package com.example.trainingapp.ui.startactivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.trainingapp.ui.activity.models.ActivityTypes
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class StartActivityViewModel : ViewModel() {
    val typeList: List<ActivityTypes> = listOf(ActivityTypes.BICYCLE, ActivityTypes.JOGGING, ActivityTypes.WALKING )
    val start = MutableLiveData<LocalDateTime>()
    val typeId = MutableLiveData(0)
    val type: LiveData<ActivityTypes> = typeId.map { typeList[it]}
    private val time = MutableLiveData(0)
    val length = MutableLiveData(0)

    private fun Double.format(digits: Int) = "%.${digits}f".format(this)

    val formattedLength: LiveData<String> = length.map {
        if (it < 1000) "$it м" else "${(it / 1000.0).format(2)} км "
    }

    val formattedTime: LiveData<String> = time.map { seconds ->
        "%02d:%02d:%02d".format(seconds / 3600, (seconds / 60) % 60, seconds % 60)
    }

    fun formattedType(context : Context): LiveData<String> = type.map { it.toString(context) }
    fun formattedType(context : Context, idx : Int): String = typeList[idx].toString(context)

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
}
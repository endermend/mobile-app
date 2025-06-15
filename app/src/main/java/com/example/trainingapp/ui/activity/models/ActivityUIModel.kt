package com.example.trainingapp.ui.activity.models

import android.content.Context
import androidx.lifecycle.MutableLiveData
import java.time.LocalDateTime

@Suppress("PropertyName")
data class ActivityUIModel(
    val _length: Int,
    val _time: Int,
    val _type: ActivityTypes,
    val _date: LocalDateTime,
    val _email: String = "user",
    val _comment: MutableLiveData<String>? = null,
    val id: Int = 0,
) {
    private fun Double.format(digits: Int) = "%.${digits}f".format(this)
    private fun endOfHour(hour: Int) =
        if (hour in 5..20) "ов" else if (hour % 10 == 1) "" else if (hour % 10 <= 4) "а" else "ов"

    private fun endOfMinute(minute: Int) =
        if (minute in 5..20) "" else if (minute % 10 == 1) "а" else if (minute % 10 <= 4) "ы" else ""

    val length: String get() = if (_length < 1000) "$_length м" else "${(_length / 1000.0).format(2)} км "
    val time: String
        get() = if (_time < 60) "$_time минут${endOfMinute(_time)}"
        else if (_time % 60 == 0) "${_time / 60} час${endOfHour(_time / 60)}"
        else "${_time / 60} час${endOfHour(_time / 60)} ${
            _time % 60
        } минут${
            endOfMinute(_time % 60)
        }"
    val type: String get() = _type.toString()
    fun type(context: Context): String = _type.toString(context)
    val date: String get() = _date.toLocalDate().toString()
    val email: String get() = _email
    val comment: String get() = _comment?.value ?: ""
}

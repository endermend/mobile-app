package com.example.trainingapp.ui.activity.models

import androidx.core.util.Predicate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.data.Activity
import com.example.trainingapp.ui.activity.DateChecker
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class ActivityViewModel : ViewModel() {
    private val _activities = MutableLiveData<ArrayList<ListItemUIModel.Activity>>().apply {
        value = arrayListOf(
            ListItemUIModel.Activity(
                ActivityUIModel(
                    2, 132, ActivityTypes.SWING, LocalDateTime.now(), "user_1"
                )
            ), ListItemUIModel.Activity(
                ActivityUIModel(
                    2000, 13, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-20), "user_1"
                )
            ), ListItemUIModel.Activity(
                ActivityUIModel(
                    10037, 34, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-12), "user_2"
                )
            ), ListItemUIModel.Activity(
                ActivityUIModel(
                    3017, 47, ActivityTypes.BICYCLE, LocalDateTime.now().plusDays(-13), "user_1"
                )
            ), ListItemUIModel.Activity(
                ActivityUIModel(
                    1001, 60, ActivityTypes.SURFING, LocalDateTime.now().plusDays(-1), "user_2"
                )
            ), ListItemUIModel.Activity(
                ActivityUIModel(
                    2001, 1, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-100), "user_2"
                )
            )
        )
    }

    fun updateActivities(activities: List<Activity>) {
        val list = _activities.value
        list?.let {
            it.removeIf { v -> v.data._email == "user" }
            it.addAll(activities.map { activity ->
                ListItemUIModel.Activity(
                    ActivityUIModel(
                        activity.length, ChronoUnit.MINUTES.between(activity.startDateTime, activity.finishDateTime).toInt() , activity.type, activity.startDateTime, id = activity.id
                    )
                )
            })
        }
        _activities.postValue(list)
    }

    fun getRecyclerList(predicate: Predicate<ListItemUIModel.Activity>): List<ListItemUIModel> {
        val recyclerList = arrayListOf<ListItemUIModel>()
        val prepList =
            activities.value?.filter { predicate.test(it) }?.sortedBy { it.data._date }?.reversed()
                ?: emptyList()
        var groupId = -1
        var changed: Boolean
        prepList.forEach {
            changed = false
            while (groupId < 0 || !groups[groupId].valid(it.data._date.toLocalDate())) {
                groupId++
                changed = true
            }
            if (changed) recyclerList.add(ListItemUIModel.Title(groups[groupId].text))
            recyclerList.add(it)
        }
        return recyclerList
    }

    val activities: LiveData<ArrayList<ListItemUIModel.Activity>> = _activities

    private val groups = listOf(DateChecker("Сегодня") { it == LocalDate.now() },
        DateChecker("Вчера") { it.plusDays(1) == LocalDate.now() },
        DateChecker("На этой неделе") { it.plusWeeks(1) > LocalDate.now() },
        DateChecker("На прошлой неделе") { it.plusWeeks(2) > LocalDate.now() },
        DateChecker("В этом месяце") { it.plusMonths(1) > LocalDate.now() },
        DateChecker("В предыдущем месяце") { it.plusMonths(2) > LocalDate.now() },
        DateChecker("Ранее") { true })
}
package com.example.trainingapp.ui.activity.models

import androidx.core.util.Predicate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trainingapp.ui.activity.DateChecker
import java.time.LocalDate
import java.time.LocalDateTime

class ActivityViewModel : ViewModel()  {
    private val _activities = MutableLiveData<List<ListItemUIModel.Activity>>().apply {
        value = listOf(
            ListItemUIModel.Activity(
                ActivityUIModel(
                    2, 132, ActivityTypes.SWING, LocalDateTime.now()
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    2000, 13, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-20), "user_1"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    10037, 34, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-12), "user_2"
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    3017, 47, ActivityTypes.BICYCLE, LocalDateTime.now().plusDays(-13)
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    1001, 60, ActivityTypes.SURFING, LocalDateTime.now().plusDays(-1)
                )
            ),
            ListItemUIModel.Activity(
                ActivityUIModel(
                    2001, 1, ActivityTypes.JOGGING, LocalDateTime.now().plusDays(-100)
                )
            )
        )
    }

    fun getRecyclerList(predicate: Predicate<ListItemUIModel.Activity>) : List<ListItemUIModel> {
        val recyclerList = arrayListOf<ListItemUIModel>()
        val prepList = activities.value?.filter { predicate.test(it) }?.sortedBy { it.data._date }?.reversed() ?: emptyList()
        var groupId = -1
        var changed: Boolean
        prepList.forEach {
            changed = false
            while (groupId < 0 || !groups[groupId].valid(it.data._date.toLocalDate())){
                groupId++
                changed = true
            }
            if (changed)
                recyclerList.add(ListItemUIModel.Title(groups[groupId].text))
            recyclerList.add(it)
        }
        return recyclerList
    }

    private val activities: LiveData<List<ListItemUIModel.Activity>> = _activities

    private val groups = listOf(
        DateChecker("Сегодня") { it == LocalDate.now() },
        DateChecker("Вчера") { it.plusDays(1) == LocalDate.now() },
        DateChecker("На этой неделе") { it.plusWeeks(1) > LocalDate.now() },
        DateChecker("На прошлой неделе") { it.plusWeeks(2) > LocalDate.now() },
        DateChecker("В этом месяце") { it.plusMonths(1) > LocalDate.now() },
        DateChecker("В предыдущем месяце") { it.plusMonths(2) > LocalDate.now() },
        DateChecker("Ранее") { true }
    )
}
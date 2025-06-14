package com.example.trainingapp.ui.activity.models

sealed class ListItemUIModel {
    data class Title(val title: String) : ListItemUIModel()
    data class Activity(val data: ActivityUIModel) : ListItemUIModel()
    data class Type(val type: String, var color: Int) : ListItemUIModel()
}

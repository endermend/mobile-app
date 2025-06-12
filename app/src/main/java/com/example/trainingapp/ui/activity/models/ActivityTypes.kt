package com.example.trainingapp.ui.activity.models

import android.content.Context
import com.example.trainingapp.R

enum class ActivityTypes (val id : Int){
    SURFING(R.string.type_surfing),
    BICYCLE(R.string.type_bicycle),
    JOGGING(R.string.type_jogging),
    SWING(R.string.type_swing);


    fun toString(context : Context): String {
        return context.getString(id)
    }
}
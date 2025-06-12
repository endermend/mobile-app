package com.example.trainingapp.ui.activity

import androidx.core.util.Predicate
import java.time.LocalDate

class DateChecker (private val _text : String, private val checker: Predicate<LocalDate>) {
    fun valid(date: LocalDate) = checker.test(date)
    val text : String get() = _text
}
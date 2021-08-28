package com.manuelcaravantes.trackmyfitness.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.FakeWorkoutRepository
import com.manuelcaravantes.trackmyfitness.data.model.Workout
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainScreenViewModel(
    workoutRepository: FakeWorkoutRepository = FakeWorkoutRepository()
) : ViewModel() {

    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> get() = _workouts

    private val _date = MutableLiveData(LocalDate.now())
    private val _stringDate = MutableLiveData("Today")
    val date: LiveData<String> get() = _stringDate


    init {
        viewModelScope.launch {
            _workouts.value = workoutRepository.getWorkouts()
        }
    }

    fun onIncrementDate() {
        val current = _date.value!!
        val nexDay = current.plusDays(1)
        _date.value= nexDay
        _stringDate.value = nexDay.toString()
    }

    fun onDecrementDate() {
        val current = _date.value!!
        val prevDay = current.minusDays(1)
        _date.value = prevDay
        _stringDate.value = prevDay.toString()
    }
}
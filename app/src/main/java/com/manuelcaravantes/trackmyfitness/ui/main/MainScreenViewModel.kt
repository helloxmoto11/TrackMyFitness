package com.manuelcaravantes.trackmyfitness.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.FakeWorkoutRepository
import com.manuelcaravantes.trackmyfitness.data.model.Workout
import kotlinx.coroutines.launch

class MainScreenViewModel(
    workoutRepository: FakeWorkoutRepository = FakeWorkoutRepository()
) : ViewModel() {

    val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> get() = _workouts

    init {
        viewModelScope.launch {
            _workouts.value = workoutRepository.getWorkouts()
        }
    }
}
package com.manuelcaravantes.trackmyfitness.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivityRepository
import com.manuelcaravantes.trackmyfitness.data.model.fakeExercises
import com.manuelcaravantes.trackmyfitness.data.util.TODAY
import com.manuelcaravantes.trackmyfitness.data.util.formatDate
import com.manuelcaravantes.trackmyfitness.di.FitnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "MainScreenViewModel"
@HiltViewModel
class MainScreenViewModel @Inject constructor(
    @FitnessRepository private val repository: FitnessActivityRepository
) : ViewModel() {

    fun setTempActivity(fitnessActivity: FitnessActivity) {
        repository.tempActivity = fitnessActivity
    }

    fun getTempActivity(): FitnessActivity {
        return repository.tempActivity
    }

    private val _date = MutableLiveData(LocalDate.now())
    private val _stringDate = MutableLiveData(TODAY)
    val date: LiveData<String> get() = _stringDate

    // TODO: 9/2/2021 fix this so it gets from repository. all crud methods update value.
    var activities = repository.getActivitiesByDate(LocalDate.now().toString())
    //private val _activities = MutableLiveData<List<FitnessActivity>> (listOf())
    //val activities: LiveData<List<FitnessActivity>> get() = _activities

    //for testing only
    init {
        viewModelScope.launch {
            for (workout in fakeExercises()) repository.addActivity(workout)
        }
    }

    /**
     * increments _date and transforms _stringDate format as "Mon, Aug. 28"
     */
    fun onIncrementDate() {
        val current = _date.value!!
        val nextDay = current.plusDays(1)
        _date.value = nextDay
        val nextDayAsString = formatDate(nextDay)
        _stringDate.value = nextDayAsString
        setWorkouts(nextDay)
    }

    fun onDecrementDate() {
        val current = _date.value!!
        val prevDay = current.minusDays(1)
        _date.value = prevDay
        val prevDayAsString = formatDate(prevDay)
        _stringDate.value = prevDayAsString
        setWorkouts(prevDay)
    }

    fun onCheckedChange(fitnessActivity: FitnessActivity)  {
        viewModelScope.launch {
            repository.updateActivity(fitnessActivity)
            Log.d(TAG, "onCheckedChange: repository updated")
        }
    }

    private fun setWorkouts(date: LocalDate) {
        viewModelScope.launch {
            activities = repository.getActivitiesByDate(date.toString())
        }
    }
}
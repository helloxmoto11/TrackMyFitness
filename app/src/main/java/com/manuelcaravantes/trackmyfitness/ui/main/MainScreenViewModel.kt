package com.manuelcaravantes.trackmyfitness.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.util.TODAY
import com.manuelcaravantes.trackmyfitness.data.util.formatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : ViewModel() {

    // TODO: 8/30/2021   val exercises = repository.exercises

    private val _date = MutableLiveData(LocalDate.now())
    private val _stringDate = MutableLiveData(TODAY)
    val date: LiveData<String> get() = _stringDate


    //this is temporary
    init {
        viewModelScope.launch {
           setWorkouts(LocalDate.now())
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

    private fun setWorkouts(date: LocalDate) {
        viewModelScope.launch {
            // TODO: 8/30/2021
        }
    }
}
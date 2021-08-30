package com.manuelcaravantes.trackmyfitness.ui.addexercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivityRepository
import com.manuelcaravantes.trackmyfitness.di.FitnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddExerciseScreenViewModel @Inject constructor(
    @FitnessRepository private val repository: FitnessActivityRepository
) : ViewModel() {

    private val _screenData = MutableLiveData(AddExerciseScreenState(FitnessActivity()))
    val screenData: LiveData<AddExerciseScreenState<FitnessActivity>> = _screenData

    fun onDataChange(fitnessActivity: FitnessActivity) {
        val e = fitnessActivity.copy(
            id = Random.nextInt(),
            name = fitnessActivity.name,
            time = fitnessActivity.time,
            distance = fitnessActivity.distance,
            date = fitnessActivity.date,
            details = fitnessActivity.details
        )

        _screenData.value = AddExerciseScreenState(e)
    }

    fun onAddExercise() {
        viewModelScope.launch {
            repository.addActivity(screenData.value?.value!!)
        }
    }


}

data class AddExerciseScreenState<T>(
    var value: T
)

enum class ExerciseFields {
    NAME, TIME, DISTANCE, DETAILS, DATE
}
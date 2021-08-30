package com.manuelcaravantes.trackmyfitness.ui.addexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddExerciseScreenViewModel @Inject constructor(

) : ViewModel() {

    private val _screenData = MutableLiveData(AddExerciseScreenState(FitnessActivity()))
    val screenData: LiveData<AddExerciseScreenState<FitnessActivity>> = _screenData

    fun onDataChange(fitnessActivity: FitnessActivity) {
        Log.d(TAG, "onDataChange: exercise is $fitnessActivity")
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
            // TODO: 8/30/2021
        }
    }


}

data class AddExerciseScreenState<T>(
    var value: T
)

enum class ExerciseFields {
    NAME, TIME, DISTANCE, DETAILS, DATE
}
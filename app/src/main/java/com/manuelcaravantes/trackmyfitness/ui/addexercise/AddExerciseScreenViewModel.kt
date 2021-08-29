package com.manuelcaravantes.trackmyfitness.ui.addexercise

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelcaravantes.trackmyfitness.data.model.Exercise
import com.manuelcaravantes.trackmyfitness.data.model.FakeExerciseRepository
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddExerciseScreenViewModel @Inject constructor(
    private val repository: FakeExerciseRepository
) : ViewModel() {

    private val _screenData = MutableLiveData(AddExerciseScreenState(Exercise()))
    val screenData: LiveData<AddExerciseScreenState<Exercise>> = _screenData

    fun onDataChange(exercise: Exercise) {
        Log.d(TAG, "onDataChange: exercise is $exercise")
        val e = exercise.copy(
            id = Random.nextInt(),
            name = exercise.name,
            time = exercise.time,
            distance = exercise.distance,
            date = exercise.date,
            details = exercise.details
        )

        _screenData.value = AddExerciseScreenState(e)
    }

    fun onAddExercise() {
        viewModelScope.launch {
            repository.addExercise(_screenData.value!!.value)
        }
    }


}

data class AddExerciseScreenState<T>(
    var value: T
)

enum class ExerciseFields {
    NAME, TIME, DISTANCE, DETAILS, DATE
}
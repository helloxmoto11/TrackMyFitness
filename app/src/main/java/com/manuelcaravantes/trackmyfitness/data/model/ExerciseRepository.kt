package com.manuelcaravantes.trackmyfitness.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import java.time.LocalDate
import javax.inject.Inject

interface ExerciseRepository {

    suspend fun getExercises(date: LocalDate)

    suspend fun addExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

}

class FakeExerciseRepository @Inject constructor(): ExerciseRepository {

    private val _exercises = MutableLiveData(fakeExercises())
    val exercises: LiveData<MutableList<Exercise>>
        get() = _exercises

    override suspend fun getExercises(date: LocalDate){
        val e = fakeExercises().filter {
            it.date == date.toString()
        }
        _exercises.value = e.toMutableList()
    }

    override suspend fun addExercise(exercise: Exercise) {
        _exercises.value?.add(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        _exercises.value?.remove(exercise)
    }
}
package com.manuelcaravantes.trackmyfitness.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

interface FitnessActivityRepository {

    suspend fun getActivity(date: LocalDate)

    suspend fun getAllActivities()

    suspend fun addActivity(fitnessActivity: FitnessActivity)

    suspend fun deleteActivity(fitnessActivity: FitnessActivity)

    suspend fun updateActivity(fitnessActivity: FitnessActivity)

}


@Singleton
class FakeFitnessActivityRepository @Inject constructor(): FitnessActivityRepository {

    private val fakeExercises = fakeExercises()
    private val _exercises = MutableLiveData(fakeExercises)
    val exercises: LiveData<MutableList<FitnessActivity>>
        get() = _exercises

    override suspend fun getActivity(date: LocalDate){
        val e = fakeExercises.filter {
            it.date == date.toString()
        }
        Log.d(TAG, "getExercises: $e")
        _exercises.value = e.toMutableList()
    }

    override suspend fun getAllActivities() {
        TODO("Not yet implemented")
    }

    override suspend fun addActivity(fitnessActivity: FitnessActivity) {
        fakeExercises.add(fitnessActivity)
        _exercises.value = fakeExercises
    }

    override suspend fun deleteActivity(fitnessActivity: FitnessActivity) {
        _exercises.value?.remove(fitnessActivity)
    }

    override suspend fun updateActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }
}
package com.manuelcaravantes.trackmyfitness.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

private const val TAG = "FakeActivityRepository"
class FakeActivityRepository @Inject constructor(
): FitnessActivityRepository {

    private val allActivities = fakeExercises()
    private val _activities: MutableLiveData<List<FitnessActivity>> = MutableLiveData(allActivities)
    val activities: LiveData<List<FitnessActivity>> = _activities

    override fun getAllActivities(): LiveData<List<FitnessActivity>> {
        _activities.value = allActivities
        return activities
    }

    override fun getActivitiesByDate(date: String): LiveData<List<FitnessActivity>> {
        val sortedList = mutableListOf<FitnessActivity>()
        allActivities.forEach { activity ->
            if (activity.date == date) {
                sortedList.add(activity)
            }
        }
        _activities.value = sortedList
        return activities
    }

    override fun searchActivitiesByName(name: String): LiveData<List<FitnessActivity>> {
        val sortedList = mutableListOf<FitnessActivity>()
        allActivities.forEach { activity ->
            if (activity.name == name) {
                sortedList.add(activity)
            }
        }
        _activities.value = sortedList
        return activities
    }

    override suspend fun addActivity(fitnessActivity: FitnessActivity) {
        allActivities += fitnessActivity
        _activities.value = allActivities
    }

    override suspend fun updateActivity(fitnessActivity: FitnessActivity) {
        Log.d(TAG, "updateActivity: ")
        for (index in allActivities.indices) {
            if (allActivities[index].id == fitnessActivity.id) {
                allActivities[index] = fitnessActivity
                _activities.value = allActivities
                break
            }
        }
    }

    override suspend fun deleteActivity(fitnessActivity: FitnessActivity) {
        for (index in allActivities.indices) {
            if (allActivities[index].id == fitnessActivity.id) {
                allActivities.removeAt(index)
                _activities.value = allActivities
                break
            }
        }
    }

    override suspend fun deleteAllActivities() {
        allActivities.clear()
        _activities.value = allActivities
    }
}
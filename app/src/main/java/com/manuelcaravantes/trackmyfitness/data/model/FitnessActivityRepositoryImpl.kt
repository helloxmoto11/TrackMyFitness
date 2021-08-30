package com.manuelcaravantes.trackmyfitness.data.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class FitnessActivityRepositoryImpl @Inject constructor(
    private val fitnessActivityDao: FitnessActivityDao
): FitnessActivityRepository {

    override  fun getAllActivities(): LiveData<List<FitnessActivity>> {
        TODO("Not yet implemented")
    }

    override  fun getActivityByDate(date: String): LiveData<List<FitnessActivity>> {
        TODO("Not yet implemented")
    }

    override  fun searchActivitiesByName(name: String): LiveData<List<FitnessActivity>> {
        TODO("Not yet implemented")
    }

    override suspend fun addActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllActivities() {
        TODO("Not yet implemented")
    }

    override suspend fun updateActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }
}
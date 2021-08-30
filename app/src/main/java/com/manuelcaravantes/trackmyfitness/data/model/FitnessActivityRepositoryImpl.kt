package com.manuelcaravantes.trackmyfitness.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import javax.inject.Inject

class FitnessActivityRepositoryImpl @Inject constructor(
    private val fitnessActivityDao: FitnessActivityDao
): FitnessActivityRepository {

    override  fun getAllActivities(): LiveData<List<FitnessActivity>> {
        return fitnessActivityDao.getAllFitnessActivities()
    }

    override  fun getActivitiesByDate(date: String): LiveData<List<FitnessActivity>> {
        return fitnessActivityDao.getFitnessActivitiesByDate(date)
    }


    override  fun searchActivitiesByName(name: String): LiveData<List<FitnessActivity>> {
        return fitnessActivityDao.searchFitnessActivitiesByName(name)
    }

    override suspend fun addActivity(fitnessActivity: FitnessActivity) {
        Log.d(TAG, "addActivity: repository")
        fitnessActivityDao.insertFitnessActivity(fitnessActivity)
    }

    override suspend fun deleteActivity(fitnessActivity: FitnessActivity) {
        fitnessActivityDao.deleteFitnessActivity(fitnessActivity)
    }

    override suspend fun deleteAllActivities() {
        fitnessActivityDao.deleteAllActivities()
    }

    override suspend fun updateActivity(fitnessActivity: FitnessActivity) {
        fitnessActivityDao.updateFitnessActivity(fitnessActivity)
    }
}
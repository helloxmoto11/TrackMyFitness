package com.manuelcaravantes.trackmyfitness.data.model

import androidx.lifecycle.LiveData

interface FitnessActivityRepository {

    fun getAllActivities(): LiveData<List<FitnessActivity>>

    fun getActivitiesByDate(date: String): LiveData<List<FitnessActivity>>

    fun searchActivitiesByName(name: String): LiveData<List<FitnessActivity>>

    suspend fun addActivity(fitnessActivity: FitnessActivity)

    suspend fun updateActivity(fitnessActivity: FitnessActivity)

    suspend fun deleteActivity(fitnessActivity: FitnessActivity)

    suspend fun deleteAllActivities()

}

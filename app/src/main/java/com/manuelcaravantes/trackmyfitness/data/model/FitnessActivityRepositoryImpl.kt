package com.manuelcaravantes.trackmyfitness.data.model

import java.time.LocalDate

class FitnessActivityRepositoryImpl(
    private val fitnessActivityDao: FitnessActivityDao
): FitnessActivityRepository {
    override suspend fun getActivity(date: LocalDate) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllActivities() {
        TODO("Not yet implemented")
    }

    override suspend fun addActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateActivity(fitnessActivity: FitnessActivity) {
        TODO("Not yet implemented")
    }
}
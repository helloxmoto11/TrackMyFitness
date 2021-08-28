package com.manuelcaravantes.trackmyfitness.data.model

import javax.inject.Inject

interface WorkoutRepository {

    suspend fun getWorkouts(): List<Workout>
}

class FakeWorkoutRepository @Inject constructor(): WorkoutRepository {

    override suspend fun getWorkouts(): List<Workout> {
        return FakeWorkouts()
    }

}
package com.manuelcaravantes.trackmyfitness.data.model

interface WorkoutRepository {

    suspend fun getWorkouts(): List<Workout>
}

class FakeWorkoutRepository: WorkoutRepository {

    override suspend fun getWorkouts(): List<Workout> {
        return FakeWorkouts()
    }

}
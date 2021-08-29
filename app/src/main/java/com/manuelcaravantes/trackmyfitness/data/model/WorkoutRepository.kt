package com.manuelcaravantes.trackmyfitness.data.model

import java.time.LocalDate
import javax.inject.Inject

interface WorkoutRepository {

    suspend fun getWorkouts(date: LocalDate): List<Workout>
}

class FakeWorkoutRepository @Inject constructor(): WorkoutRepository {

    override suspend fun getWorkouts(date: LocalDate): List<Workout> {
        val workouts = mutableListOf<Workout>()
        for (workout in fakeWorkouts()) {
            if (workout.date == date.toString()) workouts.add(workout)
        }
        return workouts
    }

}
package com.manuelcaravantes.trackmyfitness.data.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FitnessActivityDao {

    @Query("SELECT * FROM activity_table")
     fun getAllFitnessActivities(): LiveData<List<FitnessActivity>>

    @Query("SELECT * FROM activity_table WHERE date = :date ORDER BY timeStamp")
     fun getFitnessActivitiesByDate(date: String): LiveData<List<FitnessActivity>>

    @Query("SELECT * FROM activity_table WHERE name LIKE :name")
     fun searchFitnessActivitiesByName(name: String): LiveData<List<FitnessActivity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFitnessActivity(fitnessActivity: FitnessActivity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFitnessActivity(fitnessActivity: FitnessActivity)

    @Delete
    suspend fun deleteFitnessActivity(fitnessActivity: FitnessActivity)

    @Query("DELETE FROM activity_table")
    suspend fun deleteAllActivities()


}
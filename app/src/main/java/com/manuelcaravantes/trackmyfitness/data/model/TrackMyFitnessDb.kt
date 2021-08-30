package com.manuelcaravantes.trackmyfitness.data.model

import androidx.room.Database
import androidx.room.RoomDatabase

// TODO: 8/30/2021 look into changing export schema
@Database(entities = [FitnessActivity::class], version = 1, exportSchema = false)
abstract class TrackMyFitnessDb: RoomDatabase() {

    abstract fun activityDao(): FitnessActivityDao


}
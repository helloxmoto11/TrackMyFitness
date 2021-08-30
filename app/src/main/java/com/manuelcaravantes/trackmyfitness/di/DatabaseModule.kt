package com.manuelcaravantes.trackmyfitness.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivityDao
import com.manuelcaravantes.trackmyfitness.data.model.TrackMyFitnessDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//can be of object type if only contains @provides functions.
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {


    @Provides
    fun provideFitnessActivityDao(appDb: TrackMyFitnessDb): FitnessActivityDao {
        return appDb.activityDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.inMemoryDatabaseBuilder(context, TrackMyFitnessDb::class.java).build()
    }
}

package com.manuelcaravantes.trackmyfitness.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.manuelcaravantes.trackmyfitness.data.model.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

private const val TAG = "DatabaseModule"
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
    fun provideDatabase(@ApplicationContext context: Context): TrackMyFitnessDb {
        Log.d(TAG, "provideDatabase: ")
        return Room.inMemoryDatabaseBuilder(
            context,
            TrackMyFitnessDb::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }


}

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @FitnessRepository
    @Singleton
    @Binds
    abstract fun bindRepository(repository: FitnessActivityRepositoryImpl): FitnessActivityRepository

    @FakeRepository
    @Singleton
    @Binds
    abstract fun bindFakeRepository(fakeRepository: FakeActivityRepository): FitnessActivityRepository
}


@Qualifier
annotation class FakeRepository
@Qualifier
annotation class FitnessRepository





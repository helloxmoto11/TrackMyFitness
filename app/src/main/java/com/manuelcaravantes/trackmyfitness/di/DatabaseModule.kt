package com.manuelcaravantes.trackmyfitness.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.manuelcaravantes.trackmyfitness.data.model.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

//can be of object type if only contains @provides functions.
@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModule {

    @Provides
    fun provideFitnessActivityDao(appDb: TrackMyFitnessDb): FitnessActivityDao {
        return appDb.activityDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RoomDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            TrackMyFitnessDb::class.java)
            .build()
    }

    @FitnessRepository
    @Singleton
    @Binds
    abstract fun bindRepository(repository: FitnessActivityRepositoryImpl): FitnessActivityRepository

    @FakeRepository
    @Binds
    abstract fun bindFakeRepository(fakeRepository: FakeActivityRepository): FitnessActivityRepository
}

@Qualifier
annotation class FakeRepository
@Qualifier
annotation class FitnessRepository

//object CALLBACK: RoomDatabase.Callback(){
//    override fun onCreate(db: SupportSQLiteDatabase) {
//        super.onCreate(db)
//        runBlocking(Dispatchers.IO) {
//
//        }
//    }
//}


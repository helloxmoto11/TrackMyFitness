package com.manuelcaravantes.trackmyfitness.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.manuelcaravantes.trackmyfitness.data.model.*
import com.manuelcaravantes.trackmyfitness.data.util.TAG
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Qualifier
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
    fun provideDatabase(@ApplicationContext context: Context): TrackMyFitnessDb {
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
    @Binds
    abstract fun bindFakeRepository(fakeRepository: FakeActivityRepository): FitnessActivityRepository
}


@Qualifier
annotation class FakeRepository
@Qualifier
annotation class FitnessRepository



class CALLBACK: RoomDatabase.Callback(){
    @Inject
    lateinit var dao: FitnessActivityDao
    override fun onCreate(db: SupportSQLiteDatabase) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(db)
        runBlocking(Dispatchers.IO) {
            dao.insertFitnessActivity(fakeExercise())
        }
    }
}


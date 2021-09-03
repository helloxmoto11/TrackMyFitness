package com.manuelcaravantes.trackmyfitness.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "activity_table")
data class FitnessActivity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "row_id")
    var id: Int = -1,
    var name: String = "",
    var time: String = "",
    var distance: Float = 0f,
    var details: String = "",
    var date: String = "",
    var completed: Boolean = false,
    var photoUri: String = "",
    val timeStamp: Long = -1L
)


fun fakeExercises(): MutableList<FitnessActivity> {
    val yesterday = LocalDate.now().minusDays(1).toString()
    val today = LocalDate.now().toString()
    val tomorrow = LocalDate.now().plusDays(1).toString()
    val w1 = FitnessActivity(
        1,
        "Running",
        "00:45",
        5.4f,
        "Ran around the block.",
        yesterday
    )
    val w2 = FitnessActivity(
        2,
        "Gym",
        "00:45",
        0f,
        "Back and Biceps.",
        yesterday,
        false
    )
    val w3 = FitnessActivity(
        3,
        "Swimming",
        "00:25",
        1f,
        "Swam in the pool.",
        yesterday,
        false
    )

    val w4 = FitnessActivity(
        4,
        "Rowing",
        "00:45",
        1.4f,
        "Rowing.",
        yesterday
    )
    val w5 = FitnessActivity(
        5,
        "Walking",
        "00:45",
        0f,
        "Went for a Walk",
        today
    )
    val w6 = FitnessActivity(
        6,
        "Yoga",
        "01:25",
        1f,
        "Bikram!!",
        yesterday
    )
    val w7 = FitnessActivity(
        7,
        "Elliptical",
        "00:45",
        3f,
        "Bit of cardio.",
        yesterday
    )
    val w8 = FitnessActivity(
        8,
        "Hiking",
        "02:25",
        6.5f,
        "Went for a hike.",
        tomorrow
    )
    val w9 = FitnessActivity(
        9,
        "Gym",
        "00:25",
        1f,
        "Push!!",
        tomorrow
    )
    return mutableListOf(
        w1, w2, w3, w4, w5, w6, w7, w8, w9
    )
}

fun fakeExercise(id: Int, date: String): FitnessActivity {
    return FitnessActivity( id = id,name ="Jogging",time =  "00:55",distance =  5f,details =  "Ran around the block",date =  date)
}
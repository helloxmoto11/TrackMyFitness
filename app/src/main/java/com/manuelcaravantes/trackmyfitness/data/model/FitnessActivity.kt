package com.manuelcaravantes.trackmyfitness.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    var completed: Boolean = true,
    var photoUri: String = "",
    val timeStamp: Long = -1L
)


fun fakeExercises(): MutableList<FitnessActivity> {
    val w1 = FitnessActivity(
        1,
        "Running",
        "00:45",
        5.4f,
        "Ran around the neighborhood.",
        "2021-08-28"
    )
    val w2 = FitnessActivity(
        2,
        "Gym",
        "00:45",
        0f,
        "Back and Biceps.",
        "2021-08-28",
        false
    )
    val w3 = FitnessActivity(
        3,
        "Swimming",
        "00:25",
        1f,
        "Swam in the pool.",
        "2021-08-28",
        false
    )

    val w4 = FitnessActivity(
        4,
        "Rowing",
        "00:45",
        1.4f,
        "Spent some time on the rower.",
        "2021-08-29"
    )
    val w5 = FitnessActivity(
        5,
        "Walking",
        "00:45",
        0f,
        "Went for a Walk",
        "2021-08-29"
    )
    val w6 = FitnessActivity(
        6,
        "Gym",
        "00:25",
        1f,
        "LEG DAY!!",
        "2021-08-29"
    )
    val w7 = FitnessActivity(
        6,
        "Elliptical",
        "00:45",
        3f,
        "Bit of cardio.",
        "2021-08-29"
    )
    val w8 = FitnessActivity(
        6,
        "Hiking",
        "02:25",
        6.5f,
        "Went for a hike.",
        "2021-08-29"
    )
    val w9 = FitnessActivity(
        6,
        "Gym",
        "00:25",
        1f,
        "Push!!",
        "2021-08-30"
    )
    return mutableListOf(
        w1, w2, w3, w4, w5, w6, w7, w8, w9
    )
}

fun fakeExercise(): FitnessActivity {
    return FitnessActivity(1, "Jogging", "00:55", 5f, "Ran around the block", "Today")
}
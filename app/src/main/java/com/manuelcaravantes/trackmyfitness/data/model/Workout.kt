package com.manuelcaravantes.trackmyfitness.data.model

data class Workout(
    var id: Int = -1,
    var name: String = "",
    var time: String = "",
    var distance: Float = 0f,
    var details: String = ""
)







fun FakeWorkouts(): List<Workout> {
    val w1 = Workout(
        1,
        "Running",
        "00:45",
        5.4f,
        "Ran around the neighborhood."
    )
    val w2 = Workout(
        2,
        "Gym",
        "00:45",
        0f,
        "Back and Biceps."
    )
    val w3 = Workout(
        3,
        "Swimming",
        "00:25",
        1f,
        "Swam in the pool."
    )
    return listOf(
        w1, w2, w3
    )
}
package com.manuelcaravantes.trackmyfitness.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manuelcaravantes.trackmyfitness.data.model.FakeWorkout
import com.manuelcaravantes.trackmyfitness.data.model.Workout
import com.manuelcaravantes.trackmyfitness.data.util.TAG


@ExperimentalMaterialApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = viewModel()
) {
    val workouts = mainScreenViewModel.workouts.observeAsState()
    val date = mainScreenViewModel.date.observeAsState()

    Log.d(TAG, "MainScreen: ${date.value}")
    Log.d(TAG, "MainScreen: $date")
    Column(
        modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        date.value?.let {
            TodayRow(
                it,
            onIncrementDate = { mainScreenViewModel.onIncrementDate() },
            onDecrementDate = { mainScreenViewModel.onDecrementDate() })
        }
        workouts.value?.let {
            for (workout in it) {
                WorkoutCard(workout = workout)
            }
        }
    }
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}


//extract this card to be reusable. maybe keep in own file.

@ExperimentalMaterialApi
@Composable
fun WorkoutCard(
    workout: Workout = FakeWorkout()
) {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(bottom = 4.dp),
        elevation = 8.dp
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = workout.name,
                    style = MaterialTheme.typography.h6
                    )
                Checkbox(
                    checked = true,
                    onCheckedChange = { /**DO SOMETHING HERE**/ }
                )
            }
            Text(text = "Time: ${workout.time}")
            Text(text = "Distance: ${workout.distance}")
            Text(text = "Details: ${workout.details}")
        }
    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewWorkoutCard() {
    WorkoutCard()
}

@Composable
fun TodayRow(
    date: String,
    onIncrementDate: () -> Unit,
    onDecrementDate: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        IconButton(onClick = onDecrementDate) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }
        Text(
            text = date,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        IconButton(onClick = onIncrementDate) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTodayRow() {
    TodayRow(date = "Today", onIncrementDate = { /*TODO*/ }, onDecrementDate = {})

}
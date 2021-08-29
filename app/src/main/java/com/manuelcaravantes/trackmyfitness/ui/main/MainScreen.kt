package com.manuelcaravantes.trackmyfitness.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.data.model.Exercise
import com.manuelcaravantes.trackmyfitness.data.model.fakeExercise


@ExperimentalMaterialApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    showFab: (Boolean) -> Unit = {true}
) {
    showFab(true)
    val workouts = mainScreenViewModel.exercises.observeAsState()
    val date = mainScreenViewModel.date.observeAsState()

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
            if (it.isNotEmpty()) {
                for (workout in it) {
                    WorkoutCard(exercise = workout)
                }
            } else EmptyMessage()
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
    exercise: Exercise = fakeExercise()
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
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.h6
                )
                Checkbox(
                    checked = true,
                    onCheckedChange = { /**DO SOMETHING HERE**/ }
                )
            }
            Text(text = "Time: ${exercise.time}")
            Text(text = "Distance: ${exercise.distance}")
            Text(text = "Details: ${exercise.details}")
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

@Composable
fun EmptyMessage() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(72.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "error Icon",
            modifier = Modifier
                .size(64.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.empty_log_message),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
        
        )
    }
}

@Preview
@Composable
fun PreviewEmptyMessage() {
    EmptyMessage()

}

@Preview(showBackground = true)
@Composable
fun PreviewTodayRow() {
    TodayRow(date = "Today", onIncrementDate = { /*TODO*/ }, onDecrementDate = {})

}
package com.manuelcaravantes.trackmyfitness.ui.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.data.model.fakeExercise

private const val TAG = "MainScreen"

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
) {
    val date = mainScreenViewModel.date.observeAsState()
    val activities by mainScreenViewModel.activities.observeAsState()


    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        date.value?.let {
            TodayRow(
                it,
                onIncrementDate = { mainScreenViewModel.onIncrementDate() },
                onDecrementDate = { mainScreenViewModel.onDecrementDate() })
        }
        activities?.let { list ->
            if (list.isNotEmpty()) {
                LazyColumn {
                    items(list) { activity ->
                        WorkoutCard(activity) {
                            activity.completed = it
                            mainScreenViewModel.onCheckedChange(activity)
                        }
                    }
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
    fitnessActivity: FitnessActivity,
    onCheckedChange: (Boolean) -> Unit
) {
    Log.d(TAG, "WorkoutCard: workout value is $fitnessActivity")
    var checked by remember {
        mutableStateOf(fitnessActivity.completed)
    }
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier.padding(bottom = 4.dp),
        elevation = 2.dp
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
                    text = fitnessActivity.name,
                    style = MaterialTheme.typography.h6
                )
                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        checked = !checked
                        onCheckedChange(checked)
                    }
                )
            }
            Text(text = "Time: ${fitnessActivity.time}")
            Text(text = "Distance: ${fitnessActivity.distance}")
            Text(text = "Details: ${fitnessActivity.details}")
        }
    }

}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewWorkoutCard() {
    // WorkoutCard()
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

@Preview(showBackground = true)
@Composable
fun PreviewEmptyMessage() {
    EmptyMessage()
}

@Preview(showBackground = true)
@Composable
fun PreviewTodayRow() {
    TodayRow(date = "Today", onIncrementDate = { /*TODO*/ }, onDecrementDate = {})

}
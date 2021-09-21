package com.manuelcaravantes.trackmyfitness.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.ui.components.WorkoutCard

private const val TAG = "MainScreen"

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {
    val date = mainScreenViewModel.date.observeAsState()
    val activities by mainScreenViewModel.activities.observeAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
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
                    items(items = list,
                        key = { item ->
                            //You have to use a unique key to remember state in lists.
                            item.id
                        }
                    ) { activity ->
                        WorkoutCard(
                            fitnessActivity = activity,
                            onCheckedChange = {
                                activity.completed = it
                                mainScreenViewModel.onCheckedChange(activity)
                            },
                            onCardClicked = { navController.navigate("ExerciseDetailScreen") }
                        )
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
package com.manuelcaravantes.trackmyfitness.ui.addexercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.ui.addexercise.ExerciseFields.*
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme


@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    addExerciseScreenViewModel: AddExerciseScreenViewModel = hiltViewModel(),
    update: Boolean = false,
    showFab: (Boolean) -> Unit = { false }
) {
    showFab(false)

    val screenState by addExerciseScreenViewModel.screenData.observeAsState()
    val exercise = screenState!!.value

    val onDataChange: (String, ExerciseFields) -> Unit = { value, field ->
        when (field) {
            NAME -> exercise.name = value
            TIME -> exercise.time = value
            DISTANCE -> exercise.distance = value.toFloat()
            DETAILS -> exercise.details = value
            DATE -> exercise.date = value
        }
        addExerciseScreenViewModel.onDataChange(exercise)
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = if (update) "Update Exercise" else "Add Exercise",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )
        TextInputRow(
            NAME,
            exercise.name,
            "Exercise",
            onDataChange
        )
        TextInputRow(TIME, exercise.time, "Time", onDataChange)
        TextInputRow(DISTANCE, exercise.distance.toString(), "Distance", onDataChange)
        TextInputRow(DETAILS, exercise.details, "Details", onDataChange)
        TextInputRow(DATE, exercise.date, "Date", onDataChange)
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = {
                addExerciseScreenViewModel.onAddExercise()
                navController.popBackStack()
            },
            Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = if (update) "Update" else "Add",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
            )
        }
    }
}

@Composable
fun TextInputRow(
    type: ExerciseFields,
    text: String?,
    hint: String = "put hint here",
    onValueChange: (String, ExerciseFields) -> Unit
) {

//    var text by remember {
//        mutableStateOf("")
//    }
    TextField(
        value = text!!,
        onValueChange = { onValueChange(it, type) },
        label = { Text(text = hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)


    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddExerciseScreen() {
    TrackMyFitnessTheme() {
        AddExerciseScreen()
    }
}
package com.manuelcaravantes.trackmyfitness.ui.addexercise

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.data.model.fitnessActivityList
import com.manuelcaravantes.trackmyfitness.ui.addexercise.ExerciseFields.*
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme


@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
    addExerciseScreenViewModel: AddExerciseScreenViewModel = hiltViewModel(),
    update: Boolean = false,

    ) {
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
            text = if (update) "Update Fitness Activity" else "Add Fitness Activity",
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
        )
//        TextInputRow(
//            NAME,
//            exercise.name,
//            "Exercise",
//            onDataChange
//        )
        AutoCompleteTextInput(NAME, exercise.name, "Name", onDataChange)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        DateInput(DATE, exercise.date, "Date", onDataChange)
        TextInputRow(TIME, exercise.time, "Time", onDataChange)
        TextInputRow(DISTANCE, exercise.distance.toString(), "Distance", onDataChange)
        TextInputRow(DETAILS, exercise.details, "Details", onDataChange)
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
    OutlinedTextField(
        value = text!!,
        onValueChange = { onValueChange(it, type) },
        label = { Text(text = hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)


    )
}

@Composable
fun DateInput(
    type: ExerciseFields,
    text: String?,
    hint: String = "put hint here",
    onValueChange: (String, ExerciseFields) -> Unit
) {
    OutlinedTextField(
        value = text!!,
        onValueChange = { onValueChange(it, type) },
        label = { Text(text = hint) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "Calendar Icon",
                Modifier.clickable {

                }
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    )
}


@Composable
fun AutoCompleteTextInput(
    name: ExerciseFields = NAME,
    text: String = "",
    hint: String = "Hint",
    onValueChange: (String, ExerciseFields) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    var size by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Filled.ArrowDropUp
    } else Icons.Filled.ArrowDropDown

    val scrollState by remember {
        mutableStateOf(ScrollState(0))
    }

    Column(

    ) {

        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it, name) },
            label = { Text(text = hint) },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    size = coordinates.size.toSize()
                }
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) expanded = true
                },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "Expand Arrow",
                    modifier = Modifier.clickable {
                        expanded = !expanded
                    })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded },
            modifier = Modifier
                .width(with(LocalDensity.current) { size.width.toDp() })
                .height(135.dp)
                .scrollable(scrollState, Orientation.Vertical)
        ) {
            for (item in fitnessActivityList) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onValueChange(item, name)
                    }
                ) {
                    Text(text = item)
                }
            }

        }

    }

}

@Preview
@Composable
fun PreviewAutoCompleteTextField() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AutoCompleteTextInput(onValueChange = { _, _ -> })
    }
}
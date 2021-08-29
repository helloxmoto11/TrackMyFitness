package com.manuelcaravantes.trackmyfitness.ui.addexercise

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme


@Composable
fun AddExerciseScreen(
    modifier: Modifier = Modifier,
    addExerciseScreenViewModel: AddExerciseScreenViewModel? = null,
    update: Boolean = false,
    showFab: (Boolean) -> Unit = {false}
) {
    showFab(false)
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
        TextInputRow("Exercise")
        TextInputRow("Time")
        TextInputRow("Distance")
        TextInputRow("Details")
        TextInputRow("Date")
        Spacer(modifier = Modifier.weight(1f, true))
        Button(
            onClick = { /*TODO*/ },
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
fun TextInputRow(hint: String = "put hint here") {

    var text by remember {
        mutableStateOf("")
    }
    TextField(
        value = text,
        onValueChange = { text = it },
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
package com.manuelcaravantes.trackmyfitness.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.data.model.fakeExercise
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme



@ExperimentalMaterialApi
@Composable
fun WorkoutCard(
    fitnessActivity: FitnessActivity,
    onCheckedChange: (Boolean) -> Unit,
    onCardClicked: (FitnessActivity) -> Unit
) {
    var checked by remember {
        mutableStateOf(fitnessActivity.completed)
    }
    Card(
        onClick = { onCardClicked(fitnessActivity) },
        modifier = Modifier.padding(bottom = 4.dp),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.large
    ) {
        Row(Modifier.fillMaxWidth()) {

            WorkoutImage(activityType = fitnessActivity.name, modifier = Modifier.weight(2f))
            Column(
                Modifier
                    .weight(6f)
                    .padding(8.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = fitnessActivity.name,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CompleteText(
                        complete = checked,
                        onCompleteChange = { checked = it}
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

}

@Composable
fun WorkoutImage(activityType: String, modifier: Modifier) {
    val image = when (activityType) {
        "Yoga" -> painterResource(id = R.drawable.unsplash_yoga_photo)
        "Running" -> painterResource(id = R.drawable.unsplash_run_portraint_photo)
        "Hiking" -> painterResource(id = R.drawable.hiking_portrait)
        "Gym" -> painterResource(id = R.drawable.gym_photo)
        "Rowing" -> painterResource(id = R.drawable.row_photo)
        else -> painterResource(id = R.drawable.default_photo_1)
    }

    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}

@Composable
fun CompleteText(complete: Boolean, onCompleteChange: (Boolean) -> Unit) {
    if (complete) {
        Text(
            text = "Completed ",
            textDecoration = TextDecoration.LineThrough,
            modifier = Modifier.clickable {
                onCompleteChange(!complete)
            }
        )
    } else Text(
        text = "Complete? ",
        modifier = Modifier.clickable {
            onCompleteChange(!complete)
        }
    )
}

@ExperimentalMaterialApi
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun PreviewWorkoutCard() {
    TrackMyFitnessTheme() {
        WorkoutCard(fakeExercise(100, "Today"), {}) {
        }
    }
}
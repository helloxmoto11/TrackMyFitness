package com.manuelcaravantes.trackmyfitness.ui.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.data.model.FitnessActivity
import com.manuelcaravantes.trackmyfitness.ui.components.WorkoutImage
import com.manuelcaravantes.trackmyfitness.ui.main.MainScreenViewModel
import com.manuelcaravantes.trackmyfitness.ui.theme.Shapes

@Composable
fun ExerciseDetailScreen(
    navController: NavController = rememberNavController(),
    viewModel: ExerciseDetailScreenViewModel = hiltViewModel(),
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        DetailCard(viewModel.getTempActivity())
    }
}


@Composable
fun DetailCard(
    activity: FitnessActivity?
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = Shapes.large
    ) {
        Column {
            WorkoutImage(
                activityType = activity?.name!!,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
            Text(text = activity.name)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewExerciseDetailScreen() {
    ExerciseDetailScreen()
}
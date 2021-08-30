package com.manuelcaravantes.trackmyfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.ui.components.ScreenScaffold
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackMyFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainLayout()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainLayout() {
    val navController = rememberNavController()
    ScreenScaffold(navController)
}



@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewMainLayout() {
    TrackMyFitnessTheme {
        MainLayout()
    }
}

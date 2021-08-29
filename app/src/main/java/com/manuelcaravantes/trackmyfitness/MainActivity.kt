package com.manuelcaravantes.trackmyfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.manuelcaravantes.trackmyfitness.ui.components.ScreenScaffold
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
    ScreenScaffold(navController = navController)
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Track My Fitness") },
    )
}

@Composable
fun BottomBar(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope
) {

    BottomAppBar() {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Button")
            }
        }
        Spacer(Modifier.weight(1f, true))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Fab(navController: NavController) {

    FloatingActionButton(onClick = { navController.navigate("AddScreen") }
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Fab")
    }

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

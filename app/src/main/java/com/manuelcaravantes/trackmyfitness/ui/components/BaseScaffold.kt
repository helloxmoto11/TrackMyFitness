package com.manuelcaravantes.trackmyfitness.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manuelcaravantes.trackmyfitness.ui.addexercise.AddExerciseScreen
import com.manuelcaravantes.trackmyfitness.ui.detail.ExerciseDetailScreen
import com.manuelcaravantes.trackmyfitness.ui.main.MainScreen
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun ScreenScaffold(
    navController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var showFab by remember {
        mutableStateOf(true)
    }
//    Log.d(TAG, "ScreenScaffold: showFag = $showFab")
//    val onFabVisChange: (Boolean) -> Unit = { showFab = it }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomBar(scaffoldState, scope) },
        floatingActionButton = {
            if (showFab) {
                Fab(navController)
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        drawerContent = {}
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "MAINSCREEN",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("MAINSCREEN") {
              //  val viewModel: MainScreenViewModel = hiltViewModel()
                MainScreen(navController = navController)
               showFab = true
            }
            composable("AddScreen") {
                AddExerciseScreen(navController = navController)
                showFab = false
            }
            composable("ExerciseDetailScreen") {
                //ExerciseDetailScreen(navController = navController)
                ExerciseDetailScreen(navController = navController)
                showFab = false
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Track My Fitness") },
    )
}

@Composable
fun BottomBar(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    scope: CoroutineScope = rememberCoroutineScope()
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

@Preview
@Composable
fun PreviewTopBar() {
    TrackMyFitnessTheme {

        TopBar()
    }

}

@Preview
@Composable
fun PreviewBottomBar() {
    TrackMyFitnessTheme {
        BottomBar()

    }
}
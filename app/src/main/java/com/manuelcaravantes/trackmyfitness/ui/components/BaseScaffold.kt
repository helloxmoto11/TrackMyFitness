package com.manuelcaravantes.trackmyfitness.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manuelcaravantes.trackmyfitness.BottomBar
import com.manuelcaravantes.trackmyfitness.Fab
import com.manuelcaravantes.trackmyfitness.TopBar
import com.manuelcaravantes.trackmyfitness.ui.addexercise.AddExerciseScreen
import com.manuelcaravantes.trackmyfitness.ui.main.MainScreen


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
    val onFabVisChange: (Boolean) -> Unit = { showFab = it }

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
                //val viewModel = hiltViewModel<MainScreenViewModel>()
                MainScreen(showFab = onFabVisChange)
            }
            composable("AddScreen") {
                AddExerciseScreen(showFab = onFabVisChange, navController = navController)
            }
        }
    }
}
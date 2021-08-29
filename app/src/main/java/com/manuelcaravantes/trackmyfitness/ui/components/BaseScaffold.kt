package com.manuelcaravantes.trackmyfitness.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manuelcaravantes.trackmyfitness.BottomBar
import com.manuelcaravantes.trackmyfitness.Fab
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.TopBar
import com.manuelcaravantes.trackmyfitness.ui.addexercise.AddExerciseScreen
import com.manuelcaravantes.trackmyfitness.ui.main.MainScreen


@ExperimentalMaterialApi
@Composable
fun ScreenScaffold(
    screenName: String = stringResource(id = R.string.app_name),
    showFab: Boolean = true,
    navController: NavHostController
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomBar(scaffoldState, scope) },
        floatingActionButton = { if (showFab) Fab() },
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
                MainScreen()
            }
            composable("AddScreen") {
                AddExerciseScreen()
            }
        }
    }
}
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
import com.manuelcaravantes.trackmyfitness.BottomBar
import com.manuelcaravantes.trackmyfitness.Fab
import com.manuelcaravantes.trackmyfitness.R
import com.manuelcaravantes.trackmyfitness.TopBar


@ExperimentalMaterialApi
@Composable
fun ScreenScaffold(
    screenName: String = stringResource(id = R.string.app_name),
    screen: @Composable (Modifier) -> Unit,
    showFab: Boolean = true
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
    ) {
        screen(Modifier.padding(it))

    }
}
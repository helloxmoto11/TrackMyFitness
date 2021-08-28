package com.manuelcaravantes.trackmyfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.manuelcaravantes.trackmyfitness.ui.main.MainScreen
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



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


@ExperimentalMaterialApi
@Composable
fun MainLayout() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomBar(scaffoldState, scope) },
        floatingActionButton = { Fab() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        drawerContent = {}
    ) {
        MainScreen(Modifier.padding(it))
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

@Composable
fun Fab() {
    FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Fab")
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun PreviewMainLayout() {
    TrackMyFitnessTheme {
        MainLayout()
    }
}

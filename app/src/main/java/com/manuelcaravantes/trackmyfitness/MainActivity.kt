package com.manuelcaravantes.trackmyfitness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrackMyFitnessTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

@Composable
fun MainLayout() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        floatingActionButton = { Fab() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,

    ) {

    }


}

@Composable
fun TopBar() {
    TopAppBar(title = { Text(text = "Track My Fitness") } )
}

@Composable
fun BottomBar() {
    BottomAppBar() {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.high) {
            IconButton(onClick = { /*TODO*/ }) {
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

@Preview
@Composable
fun PreviewMainLayout() {
    TrackMyFitnessTheme {
        MainLayout()
    }
}

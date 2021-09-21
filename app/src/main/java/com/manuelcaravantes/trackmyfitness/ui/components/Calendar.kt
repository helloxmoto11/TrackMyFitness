package com.manuelcaravantes.trackmyfitness.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme

@Composable
fun CustomCalendar() {
    Card() {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            //HEADER
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colors.primary)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Select Date", fontSize = 16.sp)
                Text(text = "Monday, September 26th", fontSize = 24.sp)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "left arrow")
                Text(text = "September 2021")
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "right arrow")
            }
            //CALENDAR DAYS BOX
            var count = 1
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(7) {
                    Box(
                        Modifier
                            .size(40.dp)
                    ) {
                        Text(
                            text = "Mon", modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }


            repeat(6) { row ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(7) { day ->
                        Box(
                            Modifier
                                .size(40.dp)
                                .align(Alignment.CenterVertically)

                        ) {
                            Canvas(modifier = Modifier.fillMaxSize()) {
                                if (count == 12)
                                drawCircle(Color.Red)
                            }
                            Text(
                                text = "$count",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                        count++
                    }
                }
            }
            //BUTTON FRAME
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Cancel", fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "OK", fontSize = 16.sp)
                }
            }
        }
    }
}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = false)
@Composable
fun PreviewCustomCalendar() {
    TrackMyFitnessTheme() {
        CustomCalendar()
    }
}



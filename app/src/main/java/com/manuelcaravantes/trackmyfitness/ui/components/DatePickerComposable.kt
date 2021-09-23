package com.manuelcaravantes.trackmyfitness.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manuelcaravantes.trackmyfitness.data.util.Calendar
import com.manuelcaravantes.trackmyfitness.data.util.WeekDay
import com.manuelcaravantes.trackmyfitness.ui.theme.TrackMyFitnessTheme
import java.time.LocalDate
@Composable
fun CustomCalendar(
    calendar: Calendar = Calendar(),
    headerColor: Color = MaterialTheme.colors.secondary,
    buttonColor: Color = MaterialTheme.colors.secondary,
    selectedDayColor: Color = MaterialTheme.colors.secondary,
    onNegativeButtonClicked: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    onDateSelected: (LocalDate) -> Unit
) {
    var monthYear by remember {
        mutableStateOf(calendar._monthYear)
    }

    Card() {
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            CalendarHeader(
                background = headerColor,
                calendar.dayAsString
            )
            MonthSelectorRow(
                month = monthYear,
                onIncrementMonth = { monthYear = calendar.onIncrementMonth() },
                onDecrementMonth = { monthYear = calendar.onDecrementMonth() }
            )
            DayOfWeekRow()
            CalendarFrame(
                selectedDay = 15,
                selectedDayColor = selectedDayColor,
                onDateSelected = { }
            )
            ButtonLayout(
                onNegativeButtonClicked = onNegativeButtonClicked,
                onPositiveButtonClicked = onPositiveButtonClicked,
                buttonColor = buttonColor
            )
        }
    }
}



@Composable
private fun CalendarHeader(
    background: Color,
    selectedDate: String
) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(MaterialTheme.colors.secondary)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Select Date", fontSize = 16.sp)
        Text(text = selectedDate, fontSize = 30.sp)
    }
}

@Composable
fun MonthSelectorRow(
    month: String,
    onIncrementMonth: () -> Unit,
    onDecrementMonth: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onDecrementMonth) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "left arrow"
            )
        }
        Text(text = month)
        IconButton(onClick = onIncrementMonth) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "right arrow"
            )
        }
    }
}

@Composable
fun DayOfWeekRow() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(40.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(40.dp)) {
            Text(text = "Sun", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Mon", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Tue", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Wed", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Thu", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Fri", modifier = Modifier.align(Alignment.Center))
        }
        Box(Modifier.size(40.dp)) {
            Text(text = "Sat", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun CalendarFrame(
    daysInMonth: Int = 30,
    firstDayOfMonth: WeekDay = WeekDay.Sunday(),
    selectedDay: Int,
    selectedDayColor: Color,
    onDateSelected: () -> Unit
) {
    var count = 1
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
                    if (count == selectedDay)
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(color = selectedDayColor)
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
}

@Composable
fun ButtonLayout(
    negativeButtonText: String = "Cancel",
    positiveButtonText: String = "OK",
    onNegativeButtonClicked: () -> Unit,
    onPositiveButtonClicked: () -> Unit,
    buttonColor: Color
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = onNegativeButtonClicked) {
            Text(
                text = negativeButtonText,
                fontSize = 16.sp,
                color = buttonColor
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        TextButton(onClick = onPositiveButtonClicked) {
            Text(
                text = positiveButtonText,
                fontSize = 16.sp,
                color = buttonColor
            )
        }
    }

}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = false)
@Composable
fun PreviewCustomCalendar() {
    TrackMyFitnessTheme() {
        CustomCalendar(onNegativeButtonClicked = {},
            onPositiveButtonClicked = {}) {}
    }
}
package com.niranjan.khatri.androidcomposetutorial.ds.datepickers

import android.os.Build
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.niranjan.khatri.androidcomposetutorial.ds.theme.LocalTypography
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NDatePicker() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    // LocalDate to milliseconds
    val millis = selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
    DatePickerDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { /*TODO*/ }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(
            state = rememberDatePickerState(initialSelectedDateMillis = millis),
            title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    text = "Select a date", style = LocalTypography.current.headlineLarge
                )
            },
            headline = { Text("Headline") }
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun NDatePicker_Preview() {
    NDatePicker()
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DockedDatePicker() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    Column {
        // Display the selected date
        Text(text = "Selected Date: $selectedDate", style = LocalTypography.current.headlineLarge)

        // Calendar view (using a third-party library for demonstration)
        AndroidView(
            factory = { context ->
                CalendarView(context).apply {
                    setOnDateChangeListener { _, year, month, dayOfMonth ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    }
                }
            },
            update = { view ->
                // Update the CalendarView if needed (e.g., highlight selected date)}
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DockedDatePicker_Preview() {
    DockedDatePicker()
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDatePickerDemo() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    // LocalDate to milliseconds
    val selectedDateInMillis =
        selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateInMillis)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Button(onClick = { showDialog = true }) {
            Text("Open Date Picker", style = LocalTypography.current.headlineLarge)
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Selected date: $selectedDate", style = LocalTypography.current.headlineLarge)

        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        selectedDate = datePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        } ?: LocalDate.now()
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center),
                            text = "Select a date",
                            style = LocalTypography.current.headlineLarge
                        )
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ModelDatePicker_Preview() {
    ModalDatePickerDemo()
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalInputDatePickerDemo() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showDialog by remember { mutableStateOf(false) }
    // LocalDate to milliseconds
    val selectedDateInMillis =
        selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedDateInMillis)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Display the selected date in a TextField
        TextField(
            modifier = Modifier.fillMaxWidth(),
            textStyle = LocalTypography.current.headlineLarge,
            value = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            onValueChange = { }, // Prevent direct editing
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Open Date Picker")
                }
            }
        )

        // Show the DatePickerDialog when showDialog is true
        if (showDialog) {
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        selectedDate = datePickerState.selectedDateMillis?.let {
                            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
                        } ?: LocalDate.now()
                        showDialog = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    title = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center),
                            text = "Select a date",
                            style = LocalTypography.current.headlineLarge
                        )
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ModelInputDatePicker_Preview() {
    ModalInputDatePickerDemo()
}
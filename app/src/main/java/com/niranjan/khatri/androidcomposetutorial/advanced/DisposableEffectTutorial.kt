package com.niranjan.khatri.androidcomposetutorial.advanced

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.result.launch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.cancel
import androidx.core.graphics.values
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Composable
fun MyDisposableEffectExample(lifecycleOwner: LifecycleOwner) {
    DisposableEffect(key1 = lifecycleOwner) {
        // Code to execute when the effect enters the composition
        val observer = LifecycleEventObserver { source, event ->
            // Handle lifecycle events
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        // Cleanup logic to execute when the effect leaves the composition
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    // ... rest of your composable ...
}

@Composable
fun DisposableKeysExample(data: Data, lifecycleOwner: LifecycleOwner) {
    DisposableEffect(key1 = data, key2 = lifecycleOwner) {
        // ... effect setup using data and lifecycleOwner ...

        onDispose {
            // ... cleanup ...
        }
    }
}

// 1
@Composable
fun ObservingLifecycleEventsExample(lifecycleOwner: LifecycleOwner) {
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Perform action when the composable resumes
            } else if (event == Lifecycle.Event.ON_PAUSE) {
                // Perform action when the composable pauses
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    // ... rest of your composable ...
}

@Composable
fun MyComposable(viewModel: MyDisposableViewModel) {
    val data by viewModel.dataFlow.collectAsState()

    DisposableEffect(key1 = Unit) {
        val job = viewModel.startDataUpdates() // Start data updates

        onDispose {
            job.cancel() // Cancel data updates when the composable leaves
        }
    }

    Text(text = "Data: $data")
}

class MyDisposableViewModel : ViewModel() {

    private val _dataFlow = MutableStateFlow<String>("Initial Data")
    val dataFlow: StateFlow<String> = _dataFlow.asStateFlow()

    fun updateData(newData: String) {
        _dataFlow.value = newData
    }

    // Optional: Function to start data updates (e.g., from a repository)
    fun startDataUpdates(): Job {
        return viewModelScope.launch {
            // Simulate data updates
            while (true) {
                delay(1000) // Update every 1 second
                _dataFlow.value = "Data updated at ${System.currentTimeMillis()}"
            }
        }
    }
}

// 3

/***
 * <uses-permission android:name="android.permission.SENSOR_LIGHT" />
 */

@Composable
fun RegisteringCallbackExample(sensorManager: SensorManager) {
    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    DisposableEffect(key1 = sensor) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                // Handle sensor events here
                val lightValue = event.values[0]
                // Use lightValue as needed
            }

            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
                // Handle accuracy changes if needed
            }
        }
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }
    // ... rest of your composable ...
}
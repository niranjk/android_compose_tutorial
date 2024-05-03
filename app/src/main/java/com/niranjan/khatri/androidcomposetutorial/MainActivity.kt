package com.niranjan.khatri.androidcomposetutorial

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.niranjan.khatri.androidcomposetutorial.bootcamp.ComposableExpandables
import com.niranjan.khatri.androidcomposetutorial.bootcamp.ComposeBootcampScreen
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting the content
        setContent { // Starting Point
            AndroidComposeTutorialTheme {
                MyComposeBootcampApp(modifier =  Modifier.fillMaxSize())
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyComposeBootcampApp(modifier: Modifier= Modifier){
    var showBootcampScreen by rememberSaveable {  // Here we hoist our state
        mutableStateOf(true)
    }
    Surface {
        Surface(
            modifier = modifier,
            color = MaterialTheme.colorScheme.background
        ) {
            if (showBootcampScreen){
                ComposeBootcampScreen(
                    //  We pass the Event up by passing the callbacks down so we can recompose
                    onClicked = {
                        showBootcampScreen = false
                    }
                )
            } else ComposableExpandables()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidComposeTutorialTheme {
        Greeting("Android")
    }
}
package com.niranjan.khatri.androidcomposetutorial


import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.niranjan.khatri.androidcomposetutorial.bootcamp.ComposableExpandables
import com.niranjan.khatri.androidcomposetutorial.bootcamp.ComposeBootcampScreen
import com.niranjan.khatri.androidcomposetutorial.concepts.predictivebackInAndroid15.MyFirstPredictiveBackScreen
import com.niranjan.khatri.androidcomposetutorial.materialdesignsystem.theme.NAppTheme
import com.niranjan.khatri.androidcomposetutorial.ui.demo.DemoScreen
import com.niranjan.khatri.androidcomposetutorial.ui.demo.DemoShowScreen
import com.niranjan.khatri.androidcomposetutorial.ui.home.BottomNavigationScreen
import com.niranjan.khatri.androidcomposetutorial.ui.home.HomeScreen
import com.niranjan.khatri.androidcomposetutorial.ui.theme.AndroidComposeTutorialTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // splash screen
        installSplashScreen()
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        // Setting the content
        setContent {
            // Starting Point
            NAppTheme {
                // Using the Custom App Theme
               MyFirstPredictiveBackScreen()
            }
        }
    }
}

@Composable
fun AppGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "homeGraph") {
        addHomeGraph(navController)
        createDemoGraph(navController)
    }
}

@Composable
fun BottomBarScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationScreen(navigationController = navController) },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            AppGraph(navController = navController)
        }
    }
}

fun NavGraphBuilder.createDemoGraph(navController: NavHostController) =
    navigation(startDestination = demo.route, route = "demoShowGraph") {
        composable(demo.route) {
            DemoScreen { type ->
                navController.navigate("demo?type=$type")
            }
        }
        composable(
            route = "demo?type={type}",
            arguments = listOf(navArgument("type") { nullable = false }),
        ) {
            DemoShowScreen(
                type = it.arguments?.getString("type").orEmpty(),
            )
        }
    }

fun NavGraphBuilder.addHomeGraph(navController: NavHostController) =
    navigation(startDestination = "home", route = "homeGraph") {
        composable("home") {
            HomeScreen()
        }
    }

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Composable
fun MyComposeBootcampApp(modifier: Modifier = Modifier) {
    var showBootcampScreen by rememberSaveable {
        // Here we hoist our state
        mutableStateOf(true)
    }
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
        shadowElevation = 10.dp,
        tonalElevation = 10.dp,
    ) {
        if (showBootcampScreen) {
            ComposeBootcampScreen(
                //  We pass the Event up by passing the callbacks down so we can recompose
                onClicked = {
                    showBootcampScreen = false
                },
            )
        } else {
            ComposableExpandables()
        }
    }
}

@Preview(
    showBackground = true,
    heightDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark",
)
@Preview(showBackground = true, heightDp = 320)
@Composable
fun GreetingPreview() {
    AndroidComposeTutorialTheme {
        MyComposeBootcampApp()
    }
}

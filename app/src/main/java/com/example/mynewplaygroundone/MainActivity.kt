package com.example.mynewplaygroundone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynewplaygroundone.navigation.PlaygroundNavigation
import com.example.mynewplaygroundone.presentation.ui.theme.MyNewPlaygroundOneTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.mynewplaygroundone.presentation.ui.screens.LandingScreen


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyNewPlaygroundOneTheme{
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colorScheme.primary) {
        var showLandingScreen by remember { mutableStateOf(true) }

        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            PlaygroundNavigation()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyNewPlaygroundOneTheme {
//        Greeting("Android")
    }
}


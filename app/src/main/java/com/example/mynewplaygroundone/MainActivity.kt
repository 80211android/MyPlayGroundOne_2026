package com.example.mynewplaygroundone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mynewplaygroundone.navigation.PlaygroundNavigation
import com.example.mynewplaygroundone.presentation.ui.theme.MyNewPlaygroundOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyScreen()
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
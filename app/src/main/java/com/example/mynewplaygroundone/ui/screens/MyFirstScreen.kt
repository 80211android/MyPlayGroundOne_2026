package com.example.mynewplaygroundone.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mynewplaygroundone.components.SampleAppBar
import com.example.mynewplaygroundone.ui.theme.MyNewPlaygroundOneTheme


@Composable
fun MyScreen() {

    MyNewPlaygroundOneTheme {
        Scaffold(
            topBar = { SampleAppBar(
//                        navController = navController,
                elevation = 7.dp
            ) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            PostDisplay(
                modifier = Modifier
//                            .padding(innerPadding)
                    .padding(top = innerPadding.calculateTopPadding())
                    .fillMaxSize()

            )
        }
    }
}


@Composable
fun PostDisplay(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(8) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
        }
    }

}

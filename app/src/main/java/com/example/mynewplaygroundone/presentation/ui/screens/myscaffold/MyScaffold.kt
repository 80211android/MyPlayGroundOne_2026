package com.example.mynewplaygroundone.presentation.ui.screens.myscaffold


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mynewplaygroundone.R
import com.example.mynewplaygroundone.presentation.ui.theme.Orange
import com.example.mynewplaygroundone.presentation.ui.theme.White

//import com.akshat.newapplication.ui.theme.Orange
//import com.akshat.newapplication.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
// [START android_compose_components_scaffold]
@Composable
fun ScaffoldExample(onClick: () -> Unit) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Top app bar")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Orange,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {

                Button(
                    modifier = Modifier.fillMaxSize(),
                    onClick = onClick,
                    colors = ButtonColors(
                        Orange,
                        contentColor = White,
                        disabledContentColor = White,
                        disabledContainerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar",
                    )
                }

            }
        },
        floatingActionButton = {
//            FloatingActionButton(onClick = { presses++ }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }

            val myContext = LocalContext.current
            myFloatingButton({
                presses++
                Toast.makeText(myContext, " Presesses:  $presses", Toast.LENGTH_SHORT).show()
            })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                    """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
                    
                    It also contains some basic inner content, such as this text.
                    
                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}
// [END android_compose_components_scaffold]

@Composable
fun myFloatingButton(action: () -> Unit) {
    FloatingActionButton(onClick = action ) {
//        Icon(Icons.Default.Add, contentDescription = "Add")
//        Icon(Icons.Default.Add, contentDescription = "Add")
        Icon(painterResource(id = R.drawable.ic_favorite), null)


    }
}
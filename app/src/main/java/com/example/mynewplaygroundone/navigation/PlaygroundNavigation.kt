package com.example.mynewplaygroundone.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewplaygroundone.data.FakePostsRepository
import com.example.mynewplaygroundone.data.recipe.strawberryCake
import com.example.mynewplaygroundone.presentation.PlaygroundViewModel
import com.example.mynewplaygroundone.presentation.ui.screens.MyScreen
import com.example.mynewplaygroundone.presentation.ui.screens.myscaffold.ScaffoldExample
import com.example.mynewplaygroundone.presentation.ui.screens.recipe.MainFragment
import com.example.mynewplaygroundone.presentation.ui.theme.White

@Composable
fun PlaygroundNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
//        startDestination = "posts",
//        startDestination = "myrecipe",
        startDestination = "myscaffold",


    ) {

        composable("posts") {
//            val playgroundViewModel = hiltViewModel<PlaygroundViewModel>()
//            val playgroundViewModel: PlaygroundViewModel = viewModel()

            val playgroundViewModel: PlaygroundViewModel = viewModel(
                factory = PlaygroundViewModel.provideFactory(
                    postsRepository = FakePostsRepository()
                ),
            )

            MyScreen(navController = navController, playgroundViewModel = playgroundViewModel)
        }

        composable("myrecipe") {
            Surface(color = White) {
                MainFragment(strawberryCake)
            }
        }

        composable("myscaffold") {
            val onClick = {
                navController.navigate("posts") {
                    popUpTo(navController.graph.id) {
                        saveState = true
                    }
                }
            }
            ScaffoldExample(onClick)
        }

    }
}

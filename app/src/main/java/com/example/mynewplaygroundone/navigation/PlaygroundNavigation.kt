package com.example.mynewplaygroundone.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynewplaygroundone.ui.screens.MyScreen

@Composable
fun PlaygroundNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "posts"
    ) {

        composable("posts") {
//            val postsViewModel = hiltViewModel<PostsViewModel>()
//            PostsScreen(navController = navController, postsViewModel = postsViewModel)
            MyScreen()
        }

    }
}

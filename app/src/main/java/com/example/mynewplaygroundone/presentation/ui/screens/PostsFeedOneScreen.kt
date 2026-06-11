package com.example.mynewplaygroundone.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mynewplaygroundone.components.SampleAppBar
import com.example.mynewplaygroundone.model.Post
import com.example.mynewplaygroundone.presentation.PlaygroundViewModel
import com.example.mynewplaygroundone.presentation.ui.theme.MyNewPlaygroundOneTheme
import com.example.mynewplaygroundone.R


@Composable
fun MyScreen(navController: NavController, playgroundViewModel: PlaygroundViewModel = hiltViewModel()) {

    MyNewPlaygroundOneTheme {
        Scaffold(
            topBar = { SampleAppBar(
                elevation = 7.dp
            ) },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            PostDisplay(
                modifier = Modifier
//                            .padding(innerPadding)
                    .padding(top = innerPadding.calculateTopPadding())
                    .fillMaxSize(),
                playgroundViewModel,
                navController
            )
        }
    }
}


@Composable
fun PostDisplay(
    modifier: Modifier = Modifier,
    playgroundViewModel: PlaygroundViewModel,
    navController: NavController
) {

    val postsFeed by playgroundViewModel.fullPosts.collectAsStateWithLifecycle()

    val popularPosts: List<Post> = postsFeed?.allPosts ?: emptyList<Post>()

    LazyColumn(modifier = modifier) {

        items(popularPosts) { post ->

            IndiPost(post, navController)

        }
    }
}

@Composable
fun IndiPost(post: Post, navController: NavController) {

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {},
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        colors = CardColors(
            Color.White, Color.Black, Color.White, Color.White
        ),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = {
            navController.navigate("myscaffold") {
                popUpTo(navController.graph.id) {
                    saveState = true
                }
            }
        }

    ) {
        Column(
            modifier = Modifier.padding(all = 20.dp)
        ) {
            Text(stringResource(R.string.app_bar))

            Spacer(modifier = Modifier.height(12.dp))
            PostHeaderImage(post)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = post.title
            )
            Text(
                text = post.subtitle ?: "empty"
            )
        }
    }
}


@Composable
private fun PostHeaderImage(post: Post) {
    val imageModifier = Modifier
        .heightIn(min = 180.dp)
        .fillMaxWidth()
        .clip(shape = MaterialTheme.shapes.large)
    Image(
        painter = painterResource(post.imageId),
        contentDescription = null, // decorative
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
    )
}

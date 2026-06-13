package com.example.mynewplaygroundone.data

import com.example.mynewplaygroundone.data.posts.posts
import com.example.mynewplaygroundone.model.Post
import com.example.mynewplaygroundone.model.PostsFeed
import com.example.mynewplaygroundone.utils.addOrRemove
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of PostsRepository that returns a hardcoded list of
 * posts with resources after some delay in a background thread.
 */
class FakePostsRepository @Inject constructor() : PostsRepository {

    // for now, store these in memory
    private val favorites = MutableStateFlow<Set<String>>(setOf())

    private val postsFeed = MutableStateFlow<PostsFeed?>(null)

    private val postsFlowFeed = MutableStateFlow<Result<PostsFeed>?>(Result.Loading(""))

    // Used to make suspend functions that read and update state safe to call from any thread

    override suspend fun getPost(postId: String?): Result<Post> {
        return withContext(Dispatchers.IO) {
            val post = posts.allPosts.find { it.id == postId }
            if (post == null) {
                Result.Error(IllegalArgumentException("Post not found"))
            } else {
                Result.Success(post)
            }
        }
    }

    override suspend fun getPostsFeed(): Result<PostsFeed> {
        return withContext(Dispatchers.IO) {
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.Error(IllegalStateException())
            } else {
                postsFeed.update { posts }
                Result.Success(posts)
            }
        }
    }


    override suspend fun getFlowPostsFeed(): Flow<PostsFeed>  {
//        return postsFlowFeed.asStateFlow().collect {
//           it
//        }

        return flow { emit(posts) }
    }

    override val flowPostsFeed: Flow<Result<PostsFeed>>
        get() = flow { emit(Result.Success(posts)) }


    override fun observeFavorites(): Flow<Set<String>> = favorites
    override fun observePostsFeed(): Flow<PostsFeed?> = postsFeed

    override suspend fun toggleFavorite(postId: String) {
        favorites.update {
            it.addOrRemove(postId)
        }
    }

    // used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}

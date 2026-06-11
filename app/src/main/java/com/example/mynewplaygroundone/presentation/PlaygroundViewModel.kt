package com.example.mynewplaygroundone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mynewplaygroundone.data.PostsRepository
import com.example.mynewplaygroundone.model.PostsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject


@HiltViewModel
class PlaygroundViewModel @Inject constructor(private val postsRepository: PostsRepository,) : ViewModel() {


    private val _fullPosts = MutableStateFlow<PostsFeed?>(null)
    val fullPosts: StateFlow<PostsFeed?> = _fullPosts.asStateFlow()

    private val _name: MutableLiveData<String> = MutableLiveData("")

    val name: LiveData<String> = _name


    private val _posts: MutableStateFlow<String> = MutableStateFlow("")
    val posts: StateFlow<String> = _posts.asStateFlow()

    init {
        refreshFlowPosts()
    }



    fun updateTheName(newName: String) {
        _posts.value = newName
    }


    fun onNameChange(newName: String) {
        _name.value = newName
    }

    fun refreshFlowPosts() {
        // Ui state is refreshing


        viewModelScope.launch {

            postsRepository.getFlowPostsFeed().catch { throwable ->

//            postsRepository.flowPostsFeed.catch { throwable ->
//                val errorMessages = "throwable.errorMessages" + ErrorMessage(
//                    id = UUID.randomUUID().mostSignificantBits,
//                    messageId = R.string.load_error,
//                )

            }.collect { posts ->

                _fullPosts.value = posts
            }
        }
    }

    companion object {
        fun provideFactory(postsRepository: PostsRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return PlaygroundViewModel(postsRepository) as T
                }
            }
    }
}

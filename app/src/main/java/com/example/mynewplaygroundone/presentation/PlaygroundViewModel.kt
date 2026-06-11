package com.example.mynewplaygroundone.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class PlaygroundViewModel @Inject constructor() : ViewModel() {

    private val _name: MutableLiveData<String> = MutableLiveData("")

    val name: LiveData<String> = _name


    private val _posts: MutableStateFlow<String> = MutableStateFlow("")
    val posts: StateFlow<String> = _posts.asStateFlow()



    fun updateTheName(newName: String) {
        _posts.value = newName
    }


    fun onNameChange(newName: String) {
        _name.value = newName
    }
}

package com.sshpro.piff

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.Repository
import com.sshpro.piff.business.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {

    val dataState: MutableState<DataState<List<Photo>>> = mutableStateOf(DataState.Loading)

    init {
        getPhotos()
    }

    fun getPhotos() {
        viewModelScope.launch {
            dataState.value = DataState.Loading
            val photos = repository.get()
            dataState.value = DataState.Success(photos)
        }
    }
}

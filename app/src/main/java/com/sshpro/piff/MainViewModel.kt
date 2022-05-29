package com.sshpro.piff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sshpro.piff.business.DataState
import com.sshpro.piff.business.Repository
import com.sshpro.piff.business.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<List<Photo>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<Photo>>>
        get() = _dataState

    fun getPhotos() {
        viewModelScope.launch {
            repository.photosFlow
                .onEach { state -> _dataState.value = state }
                .launchIn(viewModelScope)
        }
    }
}

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

    val getPhotosDataState: MutableState<DataState<List<Photo>>> = mutableStateOf(DataState.Loading)
    val getPhotoDataState: MutableState<DataState<Photo>> = mutableStateOf(DataState.Loading)

    fun getPhotos() {
        viewModelScope.launch {
            try {
                getPhotosDataState.value = DataState.Loading
                val photos = repository.get()
                getPhotosDataState.value = DataState.Success(photos)
            }catch(exception: Exception){
                getPhotosDataState.value = DataState.Error(exception)
            }
        }
    }

    fun getPhoto(url: String) {
        viewModelScope.launch {
            try {
                getPhotoDataState.value = DataState.Loading
                val photo = repository.get(url)
                getPhotoDataState.value = DataState.Success(photo)
            } catch (exception: Exception) {
                getPhotoDataState.value = DataState.Error(exception)
            }
        }
    }
}

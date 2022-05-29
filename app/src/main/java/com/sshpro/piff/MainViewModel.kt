package com.sshpro.piff

import androidx.lifecycle.ViewModel
import com.sshpro.piff.business.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: Repository
): ViewModel() {

}

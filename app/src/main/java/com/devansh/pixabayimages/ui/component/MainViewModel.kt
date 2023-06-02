package com.devansh.pixabayimages.ui.component

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devansh.pixabayimages.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val pixabayRepository: PixabayRepository) :
    ViewModel() {

    var state: MutableState<MainState> = mutableStateOf(MainState())

    fun getImages(query: String) = viewModelScope.launch {
        state.value.loading = true
        val result = pixabayRepository.getQueryImages(query)
        result?.data?.hits?.toString()?.let { Log.d("myapiresponse" , it) }

        when (result ) {
            is Resource.Loading -> {
                state.value = MainState(loading = true)

            }
            is Resource.Error -> {
                state.value = MainState(error = state.value.error)
            }
            is Resource.Success -> {
                result.data?.hits?.let {
                    state.value = MainState(images = it)
                }
            }
        }

    }
}

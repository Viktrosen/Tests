package com.geekbrains.tests.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekbrains.tests.model.room.database.AppDatabase
import com.geekbrains.tests.model.room.entity.Repository
import com.geekbrains.tests.repository.DatabaseRepository
import kotlinx.coroutines.*

class DetailsViewModel(val repository: DatabaseRepository) : ViewModel() {

    private val _liveData = MutableLiveData<ScreenState>()
    private val liveData: LiveData<ScreenState> = _liveData

    fun subscribeToLiveData() = liveData
    fun getData() {
        _liveData.value = ScreenState.Loading
        viewModelScope.launch {
            val dbResults = repository.getData()
            if (dbResults != null) {
                _liveData.value = ScreenState.Working(dbResults)
            } else {
                _liveData.value =
                    ScreenState.Error(Throwable("Search results or total count are null"))
            }
        }
    }
}

sealed class ScreenState {
    object Loading : ScreenState()
    data class Working(val databaseResponse: List<Repository>) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
}
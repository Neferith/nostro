package com.angelus.nostro.page.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.GameSlot
import com.angelus.gamedomain.usecase.FetchGameSlotUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.Result

class MenuViewModel(val fetchGameSlotUseCase: FetchGameSlotUseCase): ViewModel() {

    private val _gameSlots = MutableStateFlow<List<GameSlot>>(emptyList())
    val gameSlots: StateFlow<List<GameSlot>> = _gameSlots.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchGameSlots()
    }

    private fun fetchGameSlots() {
        viewModelScope.launch {
            fetchGameSlotUseCase().fold(
                onSuccess = { slots -> _gameSlots.value = slots },
                onFailure = { exception -> _error.value = exception.message }
            )
        }
    }


    fun startNewGame() {

    }
}
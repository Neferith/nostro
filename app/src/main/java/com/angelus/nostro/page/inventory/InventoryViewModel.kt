package com.angelus.nostro.page.inventory

import androidx.lifecycle.ViewModel
import com.angelus.nostro.coordinator.IntentoryPosition
import com.angelus.playerdomain.usecase.ObservePlayerUseCase

class InventoryViewModel(
    startPosition: IntentoryPosition,
    useCases: UseCases
): ViewModel() {

    data class UseCases(val observePlayerUseCase: ObservePlayerUseCase)
}
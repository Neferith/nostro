package com.angelus.nostro.page.newgame

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.angelus.gamedomain.entities.AttributesModifier
import com.angelus.gamedomain.entities.CharacterGender
import com.angelus.gamedomain.entities.CharacterSensitivity
import com.angelus.gamedomain.entities.CharacterSize
import com.angelus.gamedomain.entities.CharacterWeight

class NewGameViewModel: ViewModel() {

    enum class STEP {
        GENDER, SIZE, WEIGHT, SENSITIVITY
    }

    val stepOrder: List<STEP> = listOf(STEP.GENDER, STEP.SIZE, STEP.WEIGHT, STEP.SENSITIVITY)

    private var currentStep: MutableState<STEP> = mutableStateOf<STEP>(STEP.GENDER)
    val currentStepState:State<STEP> = currentStep

    private var _genderState:MutableState<CharacterGender?> = mutableStateOf<CharacterGender?>(null)
    val genderState:State<CharacterGender?> = _genderState

    private var _sizeState:MutableState<CharacterSize?> = mutableStateOf<CharacterSize?>(null)
    val sizeState:State<CharacterSize?> = _sizeState

    private var _currentWeight: MutableState<CharacterWeight?> = mutableStateOf(null)
    val currentWeightState: State<CharacterWeight?> = _currentWeight

    private var _currentSensitivity: MutableState<CharacterSensitivity?> = mutableStateOf(null)
    val currentSensitivity: State<CharacterSensitivity?> = _currentSensitivity

    fun updateGender(gender: CharacterGender) {
        this._genderState.value = gender
    }

    fun updateSize(size: CharacterSize) {
        this._sizeState.value = size
    }

    fun updateWeight(weight: CharacterWeight) {
        this._currentWeight.value = weight
    }

    fun updateSensitivity(sensitivity: CharacterSensitivity) {
        this._currentSensitivity.value = sensitivity
    }

    private fun checkCurrentStep(): Boolean = when (currentStepState.value) {
        STEP.GENDER -> genderState.value != null
        STEP.SIZE -> sizeState.value != null // À compléter plus tard
        STEP.WEIGHT -> _currentWeight.value != null // À compléter plus tard
        STEP.SENSITIVITY -> _currentSensitivity.value != null // À compléter plus tard
    }

    fun nextStep() {
        if(checkCurrentStep()) {
            currentStep.value = stepOrder.nextStep(currentStep.value )
        }
    }

    val totalPointsState: State<AttributesModifier> = derivedStateOf {
        var total: AttributesModifier = AttributesModifier(0,0,0,0)

        genderState.value?.modifier?.let {
            total += it
        }
        sizeState.value?.modifier?.let {
            total += it
        }

        currentWeightState.value?.modifier?.let {
            total += it
        }

        currentSensitivity.value?.modifier?.let {
            total += it
        }

        total

    }
}

fun <T> List<T>.nextStep(current: T): T {
    val currentIndex = indexOf(current)
    return if (currentIndex != -1 && currentIndex + 1 < size) this[currentIndex + 1] else current
}

fun AttributesModifier.merge(other: AttributesModifier): AttributesModifier {
    return AttributesModifier(
        musculature = this.musculature + other.musculature,
        flexibility = this.flexibility + other.flexibility,
        brain = this.brain + other.brain,
        vitality = this.vitality + other.vitality
    )
}
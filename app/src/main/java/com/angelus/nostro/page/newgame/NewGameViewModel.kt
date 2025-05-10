package com.angelus.nostro.page.newgame

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.character.Attributes
import com.angelus.gamedomain.entities.character.AttributesModifier
import com.angelus.gamedomain.entities.character.Background
import com.angelus.gamedomain.entities.character.Character
import com.angelus.gamedomain.entities.character.CharacterDescription
import com.angelus.gamedomain.entities.character.CharacterGender
import com.angelus.gamedomain.entities.character.CharacterLevel
import com.angelus.gamedomain.entities.character.CharacterName
import com.angelus.gamedomain.entities.character.CharacterSensitivity
import com.angelus.gamedomain.entities.character.CharacterSize
import com.angelus.gamedomain.entities.character.CharacterSkills
import com.angelus.gamedomain.entities.character.CharacterWeight
import com.angelus.gamedomain.entities.item.Inventory
import com.angelus.gamedomain.usecase.GetAllBackgroundStoriesUseCase
import com.angelus.gamedomain.usecase.GetStartPositionUseCase
import com.angelus.playerdomain.entities.Player
import com.angelus.playerdomain.usecase.InitializePlayerParams
import com.angelus.playerdomain.usecase.InitializePlayerUseCase
import kotlinx.coroutines.launch

data class NewGameUseCases(
    val getAllBackgroundStoriesUseCase: GetAllBackgroundStoriesUseCase,
    val getStartPositionUseCase: GetStartPositionUseCase,
    val inialPlayerUseCase: InitializePlayerUseCase
)

class NewGameViewModel(
    val useCases: NewGameUseCases
) :
    ViewModel() {

    enum class STEP(val order: Int) {
        NAME(1),
        GENDER(2),
        SIZE(3),
        WEIGHT(4),
        SENSITIVITY(5),
        BACKGROUND(6)
    }

    private val backgroundTypes = useCases.getAllBackgroundStoriesUseCase()

    private val stepOrder: List<STEP> = enumValues<STEP>().sortedBy { it.order }

    private var currentStep: MutableState<STEP> = mutableStateOf(STEP.NAME)
    val currentStepState: State<STEP> = currentStep

    private var _nameState: MutableState<CharacterName?> = mutableStateOf(null)
    val nameState: State<CharacterName?> = _nameState

    private var _genderState: MutableState<CharacterGender?> = mutableStateOf(null)
    val genderState: State<CharacterGender?> = _genderState

    private var _sizeState: MutableState<CharacterSize?> = mutableStateOf(null)
    val sizeState: State<CharacterSize?> = _sizeState

    private var _currentWeight: MutableState<CharacterWeight?> = mutableStateOf(null)
    val currentWeightState: State<CharacterWeight?> = _currentWeight

    private var _currentSensitivity: MutableState<CharacterSensitivity?> = mutableStateOf(null)
    val currentSensitivity: State<CharacterSensitivity?> = _currentSensitivity

    private var _currentBackground: MutableState<Background?> = mutableStateOf(null)
    val currentBackground: State<Background?> = _currentBackground


    private var _currentCharacterStory: MutableState<List<Background>> = mutableStateOf(emptyList())
    private val currentCharacterStoryState: State<List<Background>> = _currentCharacterStory

    private var _currentAttributes: MutableState<Attributes> = mutableStateOf(Attributes.Empty)
    val currentAttributes: State<Attributes> = _currentAttributes

    private var _playerResult: MutableState<Result<Player>?> = mutableStateOf(null)
     val playerResult: State<Result<Player>?> = _playerResult

    fun updateFirstname(firstname: String) {
        this._nameState.value = CharacterName(firstname, _nameState.value?.lastname ?: "")
    }

    fun updateLastname(lastname: String) {
        this._nameState.value =
            CharacterName(_nameState.value?.firstname ?: "", lastname = lastname)
    }

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

    fun updateBackground(background: Background) {
        _currentBackground.value = background
        //this._currentCharacterStory.value = _currentCharacterStory.value + background
    }

    fun checkCurrentStep(): Boolean = when (currentStepState.value) {
        STEP.NAME -> (nameState.value?.firstname?.isNotEmpty() ?: false && nameState.value?.lastname?.isNotEmpty() ?: false)
        STEP.GENDER -> genderState.value != null
        STEP.SIZE -> sizeState.value != null // À compléter plus tard
        STEP.WEIGHT -> _currentWeight.value != null // À compléter plus tard
        STEP.SENSITIVITY -> _currentSensitivity.value != null // À compléter plus tard
        STEP.BACKGROUND -> {
            _currentBackground.value != null
            // _currentCharacterStory.value.size == backgroundTypes.size
        }


    }

    fun checkIsLastStep(): Boolean {
        return _currentCharacterStory.value.size == backgroundTypes.size
    }

    fun nextStep() {

        when (currentStep.value) {
            STEP.NAME -> {

            }

            STEP.GENDER -> genderState.value?.modifier?.let {
                _currentAttributes.value = _currentAttributes.value.applyModifier(it)
            }

            STEP.SIZE -> sizeState.value?.modifier?.let {
                _currentAttributes.value = _currentAttributes.value.applyModifier(it)
            }

            STEP.WEIGHT -> currentWeightState.value?.modifier?.let {
                _currentAttributes.value = _currentAttributes.value.applyModifier(it)
            }

            STEP.SENSITIVITY -> currentSensitivity.value?.modifier?.let {
                _currentAttributes.value = _currentAttributes.value.applyModifier(it)
            }

            STEP.BACKGROUND -> _currentBackground.value?.attributesModifier?.let {
                _currentAttributes.value = _currentAttributes.value.applyModifier(it)
            }

        }

        // HOTFIX FOR SELECT BACKGROUND
        val currentBackground = _currentBackground.value
        if (currentBackground != null) {

            this._currentCharacterStory.value += currentBackground
            _currentBackground.value = null
        }
        // END OF HOTFIX


        if (checkCurrentStep()) {


            currentStep.value = stepOrder.nextStep(currentStep.value)
        }


    }

    fun submitCharacter() {


        val name: CharacterName? = nameState.value
        val gender: CharacterGender? = genderState.value
        val size: CharacterSize? = sizeState.value
        val weight: CharacterWeight? = _currentWeight.value
        val sensitivity: CharacterSensitivity? = currentSensitivity.value
        val stories = _currentCharacterStory.value

        if (name != null &&
            gender != null &&
            size != null &&
            weight != null && sensitivity != null
        ) {


            viewModelScope.launch {
                // TODO: Move here
                val character = Character(
                    id = Character.MAIN_CHARACTER,
                    mainAttributes = currentAttributes.value,
                    characterLevel = CharacterLevel(0, 0),
                    description = CharacterDescription(
                        name = name,
                        gender = gender,
                        size = size,
                        weight = weight,
                        sensitivity = sensitivity,
                        background = stories.map { it.id }
                    ),
                    skills = CharacterSkills(
                        skills = emptyMap()
                    ),
                    inventory = Inventory()
                )
                val startPosition = useCases.getStartPositionUseCase()
                 val result = useCases.inialPlayerUseCase(InitializePlayerParams(
                    startPosition = startPosition,
                    character = character
                ))
                _playerResult.value = result
            }
        }

    }

    val currentBackgroundList: State<List<Background>?> = derivedStateOf {
        if (backgroundTypes.size > currentCharacterStoryState.value.size) {
            backgroundTypes[currentCharacterStoryState.value.size].backgrounds
        } else {
            null
        }
    }


    val totalPointsState: State<AttributesModifier> = derivedStateOf {
        var total = AttributesModifier(0, 0, 0, 0)

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

        currentBackground.value?.attributesModifier?.let {
            total += it
        }

        currentCharacterStoryState.value?.forEach {
            total += it.attributesModifier
        }

        total

    }
}

fun <T> List<T>.nextStep(current: T): T {
    val currentIndex = indexOf(current)
    return if (currentIndex != -1 && currentIndex + 1 < size) this[currentIndex + 1] else current
}

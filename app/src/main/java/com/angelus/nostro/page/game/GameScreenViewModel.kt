package com.angelus.nostro.page.game

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.ObserveTurnUseCase
import com.angelus.mapdomain.entities.Panorama
import com.angelus.mapdomain.repository.MoveType
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCase
import com.angelus.mapdomain.usecase.CheckMoveParams
import com.angelus.mapdomain.usecase.GetPanoramaUseCase
import com.angelus.mapdomain.usecase.ObserveCurrentMapUseCase
import com.angelus.nostro.component.MoveAction
import com.angelus.playerdomain.usecase.ChangePlayerZoneParams
import com.angelus.playerdomain.usecase.ChangePlayerZoneUseCase
import com.angelus.playerdomain.usecase.MovePlayerParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class GameScreenViewModel(
    params: Params,
    private val gameUseCases: GameUseCases,
    private val playerUseCases: PlayerUseCases,
    private val mapUseCases: MapUseCases
) : ViewModel() {

    val movePlayerUseCase get() = playerUseCases.movePlayerUseCase
    val rotatePlayerUseCase get() = playerUseCases.rotatePlayerUseCase
    val observePlayerUseCase get() = playerUseCases.observePlayerUseCase
    val observeCurrentMapUseCase get() = mapUseCases.observeCurrentMapUseCase
    val getPanoramaUseCase get() = mapUseCases.getPanoramaUseCase
    val checkMoveInMapUseCase get() = mapUseCases.checkMoveInMapUseCase
    val observeTurnUseCase get() = gameUseCases.observeTurnUseCase
    val nextTurnUseCase get() = gameUseCases.nextTurnUseCase

    data class PlayerUseCases(
        val movePlayerUseCase: com.angelus.playerdomain.usecase.MovePlayerUseCase,
        val rotatePlayerUseCase: com.angelus.playerdomain.usecase.RotatePlayerUseCase,
        val observePlayerUseCase: com.angelus.playerdomain.usecase.ObservePlayerUseCase,
        val changePlayerZoneUseCase: ChangePlayerZoneUseCase
    )

    data class MapUseCases(
        val observeCurrentMapUseCase: ObserveCurrentMapUseCase,
        val getPanoramaUseCase: GetPanoramaUseCase,
        val checkMoveInMapUseCase: CheckMoveInMapUseCase
    )

    data class GameUseCases(
        val observeTurnUseCase: ObserveTurnUseCase,
        val nextTurnUseCase: NextTurnUseCase
    )

    data class Params(val playerId: String)

    private var _panoramaState: MutableState<Panorama?> = mutableStateOf(null)
    val panoramaState: State<Panorama?> = _panoramaState

    // Observe le joueur courant via le UseCase
    val currentPlayer: StateFlow<com.angelus.playerdomain.entities.Player?> = observePlayerUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val currentMap: StateFlow<com.angelus.mapdomain.entities.GameMap?> = observeCurrentMapUseCase("")
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val currentTurn: StateFlow<Turn?> = observeTurnUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null )

    init {
        observeTurnUseCase().onEach { newTurn ->
            // Déclencher un événement à chaque fois que le tour change
            handleNewTurn(newTurn)
        }.launchIn(viewModelScope)

        currentPlayer.onEach { player ->

            if(player == null) {
                null
            } else {
                val panorama = getPanoramaUseCase(player.entityPosition)
                if (panorama != null) {
                    _panoramaState.value = panorama
                   // _panoramaState.emit(panorama)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleNewTurn(newTurn: Turn?) {
        // Logique à exécuter à chaque nouveau tour, par exemple
        if (newTurn != null) {
            when (newTurn.type) {
                is TurnType.NPC -> {
                    // TODO: LOCK UI
                    executeNPCTurn(newTurn)
                }
                is TurnType.PLAYER -> {
                        // TODO: UNLOCK UI AFTER DELAY
                }
            }
        }
    }

    private fun executeNPCTurn(turn: Turn) {

        nextTurnUseCase()  // Passe au tour suivant

    }



 /*   val panoramState: State<Panorama?> = derivedStateOf {

    }
    val panoramState: StateFlow<com.angelus.mapdomain.entities.Panorama?> = combine(
        currentPlayer,
        currentMap
    ) { player, map ->
        if(player == null) {
            null
        } else {
            getPanoramaUseCase(player.entityPosition)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)*/

    fun processMoveAction(action: MoveAction) {
        val player = currentPlayer.value
            ?: // On ne peut pas exécuter l'action si le joueur n'est pas initialisé
            return

        viewModelScope.launch {
            try {
                when (action) {
                    MoveAction.FORWARD,
                    MoveAction.BACKWARD,
                    MoveAction.STRAFE_LEFT,
                    MoveAction.STRAFE_RIGHT -> {
                        val direction = action.toDirection()
                        val moveType = checkMoveInMapUseCase(CheckMoveParams(
                            player.entityPosition,
                            action.toDirection()
                        )
                        )
                        if(movePlayer(moveType, direction)) {
                            nextTurnUseCase()
                        }
                        /*if(checkMoveInMapUseCase(
                                CheckMoveParams(
                                    player.entityPosition,
                                    action.toDirection()
                                )
                            )) {
                            movePlayerUseCase(
                                MovePlayerParams(
                                    direction
                                )
                            )
                            nextTurnUseCase()
                        } else {
                            // TODO: QUe faire
                        }*/

                    }

                    MoveAction.ROTATE_LEFT, MoveAction.ROTATE_RIGHT -> {
                        val rotation = action.toRotation()
                        rotatePlayerUseCase(
                            com.angelus.playerdomain.usecase.RotatePlayerParams(
                                rotation
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                // Gérer l'erreur (ex: affichage d'un message d'erreur)
            }
        }
    }

    suspend fun movePlayer(moveType: MoveType, direction: Direction): Boolean {
        when(moveType) {
            MoveType.blocked -> return false
            is MoveType.transition -> {
                playerUseCases.changePlayerZoneUseCase(ChangePlayerZoneParams(moveType.position))
                return true
            }
            MoveType.walkable -> {
                movePlayerUseCase(
                    MovePlayerParams(
                        direction
                    )
                )
                return true
            }
        }
    }


    private fun MoveAction.toDirection(): Direction = when (this) {
        MoveAction.FORWARD -> Direction.FORWARD
        MoveAction.BACKWARD -> Direction.BACKWARD
        MoveAction.STRAFE_LEFT -> Direction.LEFT
        MoveAction.STRAFE_RIGHT -> Direction.RIGHT
        else -> throw IllegalArgumentException("Unknown direction action: $this")
    }

    private fun MoveAction.toRotation(): Rotation = when (this) {
        MoveAction.ROTATE_LEFT -> Rotation.LEFT
        MoveAction.ROTATE_RIGHT -> Rotation.RIGHT
        else -> throw IllegalArgumentException("Unknown rotation action: $this")
    }
}

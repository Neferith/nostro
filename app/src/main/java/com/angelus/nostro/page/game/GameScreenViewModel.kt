package com.angelus.nostro.page.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.ObserveTurnUseCase
import com.angelus.mapdomain.usecase.CheckMoveInMapUseCase
import com.angelus.mapdomain.usecase.GetPanoramaUseCase
import com.angelus.mapdomain.usecase.ObserveCurrentMapUseCase
import com.angelus.nostro.component.MoveAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
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
        val observePlayerUseCase: com.angelus.playerdomain.usecase.ObservePlayerUseCase
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

    // Observe le joueur courant via le UseCase
    val currentPlayer: StateFlow<com.angelus.playerdomain.entities.Player?> = observePlayerUseCase(params.playerId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val currentMap: StateFlow<com.angelus.mapdomain.entities.GameMap?> = observeCurrentMapUseCase("")
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    val currentTurn: StateFlow<Turn?> = observeTurnUseCase().stateIn(viewModelScope, SharingStarted.Lazily, null )

    init {
        observeTurnUseCase().onEach { newTurn ->
            // Déclencher un événement à chaque fois que le tour change
            handleNewTurn(newTurn)
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


    val panoramState: StateFlow<com.angelus.mapdomain.entities.Panorama?> = combine(
        currentPlayer,
        currentMap
    ) { player, map ->
        if(player == null) {
            null
        } else {
            getPanoramaUseCase(player.entityPosition)
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun processMoveAction(action: MoveAction) {
        val player = currentPlayer.value
            ?: // On ne peut pas exécuter l'action si le joueur n'est pas initialisé
            return

        viewModelScope.launch {
            try {
                when (action) {
                    MoveAction.FORWARD, MoveAction.BACKWARD, MoveAction.STRAFE_LEFT, MoveAction.STRAFE_RIGHT -> {
                        val direction = action.toDirection()
                        if(checkMoveInMapUseCase(
                                com.angelus.mapdomain.usecase.CheckMoveParams(
                                    player.entityPosition,
                                    action.toDirection()
                                )
                            )) {
                            movePlayerUseCase(
                                com.angelus.playerdomain.usecase.MovePlayerParams(
                                    player.id,
                                    direction
                                )
                            )
                            nextTurnUseCase()
                        } else {
                            // TODO: QUe faire
                        }

                    }

                    MoveAction.ROTATE_LEFT, MoveAction.ROTATE_RIGHT -> {
                        val rotation = action.toRotation()
                        rotatePlayerUseCase(
                            com.angelus.playerdomain.usecase.RotatePlayerParams(
                                player.id,
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

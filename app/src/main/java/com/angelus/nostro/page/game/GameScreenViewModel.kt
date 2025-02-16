package com.angelus.nostro.page.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.entities.Rotation
import com.angelus.gamedomain.usecase.MovePlayerParams
import com.angelus.gamedomain.usecase.MovePlayerUseCase
import com.angelus.gamedomain.usecase.ObservePlayerUseCase
import com.angelus.gamedomain.usecase.RotatePlayerParams
import com.angelus.gamedomain.usecase.RotatePlayerUseCase
import com.angelus.nostro.component.MoveAction
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GameScreenViewModel(
    params: Params,
    private val useCases: UseCases
) : ViewModel() {

    val movePlayerUseCase get() = useCases.movePlayerUseCase
    val rotatePlayerUseCase get() = useCases.rotatePlayerUseCase
    val observePlayerUseCase get() = useCases.observePlayerUseCase

    data class UseCases(
        val movePlayerUseCase: MovePlayerUseCase,
        val rotatePlayerUseCase: RotatePlayerUseCase,
        val observePlayerUseCase: ObservePlayerUseCase
    )

    data class Params(val playerId: String)

    // Observe le joueur courant via le UseCase
    val currentPlayer: StateFlow<Player?> = observePlayerUseCase(params.playerId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun processMoveAction(action: MoveAction) {
        val player = currentPlayer.value
            ?: // On ne peut pas exécuter l'action si le joueur n'est pas initialisé
            return

        viewModelScope.launch {
            try {
                when (action) {
                    MoveAction.FORWARD, MoveAction.BACKWARD, MoveAction.STRAFE_LEFT, MoveAction.STRAFE_RIGHT -> {
                        val direction = action.toDirection()
                        movePlayerUseCase(MovePlayerParams(player.id, direction))
                    }

                    MoveAction.ROTATE_LEFT, MoveAction.ROTATE_RIGHT -> {
                        val rotation = action.toRotation()
                        rotatePlayerUseCase(RotatePlayerParams(player.id, rotation))
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

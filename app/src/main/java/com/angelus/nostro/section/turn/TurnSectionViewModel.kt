package com.angelus.nostro.section.turn

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.entities.Turn
import com.angelus.gamedomain.entities.TurnType
import com.angelus.gamedomain.usecase.NextTurnUseCase
import com.angelus.gamedomain.usecase.ObserveTurnUseCase
import com.angelus.mapdomain.usecase.CheckVisibilityUseCase
import com.angelus.nostro.page.game.GameScreenViewModel.GameUseCases
import com.angelus.playerdomain.usecase.GetPlayerUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TurnSectionViewModel(
    private val gameUseCases: GameUseCases
): ViewModel() {

    val observeTurnUseCase get() = gameUseCases.observeTurnUseCase


    private var _showPlayer: MutableState<Boolean> = mutableStateOf(false)
    val showPlayer: State<Boolean> = _showPlayer


    data class GameUseCases(
        val getPlayerUseCase: GetPlayerUseCase,
        val observeTurnUseCase: ObserveTurnUseCase,
        val nextTurnUseCase: NextTurnUseCase,
        val checkVisibilityUseCase: CheckVisibilityUseCase,
      //  val checkFactionUseCase: CheckFactionUseCase
    )

    init {
        observeTurnUseCase().onEach { newTurn ->
          //  refreshCurrentPanorama()
            // Déclencher un événement à chaque fois que le tour change
            handleNewTurn(newTurn)
        }.launchIn(viewModelScope)

    }

    private fun handleNewTurn(newTurn: Turn?) {
        // Logique à exécuter à chaque nouveau tour, par exemple
        if (newTurn != null) {
            val type = newTurn.type
            when (type) {
                is TurnType.NPC -> {
                    // TODO: LOCK UI
                    executeNPCTurn(type)
                }
                is TurnType.PLAYER -> {
                    // TODO: UNLOCK UI AFTER DELAY
                }
            }
        }
    }

    private fun executeNPCTurn(turnType: TurnType.NPC) {


        viewModelScope.launch {
            val player  = gameUseCases.getPlayerUseCase().getOrNull()

            var futurPosition: Position? = null
            player?.let {

                futurPosition = gameUseCases.checkVisibilityUseCase(turnType.entityPosition, player.entityPosition, 4)
                val showPlayer = futurPosition != null
                _showPlayer.value = showPlayer
                if(showPlayer) {
                    //check
                }
               // _showPlayer.value   != null


            }
            gameUseCases.nextTurnUseCase()
        }

        // Vérifier s'il y a un player aux alentours. En vue, ou non.
        // Est ce que ça change mes objectifs Hostile ou non ?
        // Vérifier s'il y a un NPC aux alentours. En vue, ou non.
        // Est ce que ça change mes objectifs

        // Liste d'objectif par NPC :
        // 1/ Attaquer ou fuir hostile
        // 2/ Aller vers son objectif

        //  nextTurnUseCase()  // Passe au tour suivant

    }
}
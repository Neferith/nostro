package com.angelus.nostro.page.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.angelus.data.gamedata.data.PlayerDataSourceImpl
import com.angelus.data.gamedata.repository.PlayerRepositoryImpl
import com.angelus.gamedomain.entities.Direction
import com.angelus.gamedomain.entities.Player
import com.angelus.gamedomain.entities.Position
import com.angelus.gamedomain.repository.PlayerRepository
import com.angelus.gamedomain.usecase.MovePlayerUseCaseImpl
import com.angelus.nostro.MainNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameScreenViewModel {
    val repository: PlayerRepository = PlayerRepositoryImpl(PlayerDataSourceImpl())
    val moveUseCase: MovePlayerUseCaseImpl = MovePlayerUseCaseImpl(repository)

  //  val player =  //repository.movePlayer("", 1, Direction.NORTH)

    private val _player = MutableStateFlow<Player>(Player(Position(0,0, Direction.NORTH)))
    val player: StateFlow<Player> = _player

    fun movePlayer() {
        CoroutineScope(Dispatchers.Main).launch {
            _player.value = repository.movePlayer("", 1, Direction.NORTH)
          //   this.player = repository.movePlayer("", 1, Direction.NORTH)
        }
    }
}


val gameScreenViewModel = GameScreenViewModel()

@Composable
fun GameScreen(navigator: MainNavigator, viewModel: GameScreenViewModel) {
    val playerState = viewModel.player.collectAsState()
    val text = remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Joueur : ${playerState.value.position.y }")
        OutlinedTextField(value = text.value, onValueChange = {
            text.value = it
        })

        Button(onClick = {

            viewModel.movePlayer()
            //navController.navigate(route = Screen.Detail.route + "?text=${text.value}")
        }) {
            Text("Avancer")
        }
    }
}